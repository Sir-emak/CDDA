package pl.seremak.service;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import pl.seremak.domain.CRFOP;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.List;

@ManagedBean(name = "gmlService")
@ViewScoped
public class GMLService implements Serializable {

    private StringBuilder gml;

    public StringBuilder getGml() {
        return gml;
    }

    @PostConstruct
    public void init() {
        gml = new StringBuilder();
    }

    public void prepareGML(List<CRFOP> crfops) throws Exception {
        initGML();
        for (CRFOP crfop: crfops) {
            appendToGML(crfop);
        }
        finishGML();
    }

    private void initGML() throws Exception {
        init();
        File src = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/gmls/header.gml"));
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(src));
        gml.append(IOUtils.toString(reader));
    }

    private void finishGML() {
        gml.append("</gml:FeatureCollection>\n");
    }

    private void appendToGML(CRFOP crfop) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://sdi.gdos.gov.pl/inspire/wfs?version=2.0.0&service=WFS&request=GetFeature&srsName=urn:x-ogc:def:crs:EPSG:3035&typeName=ps:ProtectedSite&featureID="+crfop.getInspireidns()+"."+crfop.getInspireidlocal());

            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                if (response.getStatusLine().getStatusCode() != 200)
                    new Exception();

                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    InputStream instream = entity.getContent();
                    try {

                        StringWriter writer = new StringWriter();
                        IOUtils.copy(instream, writer, "UTF-8");
                        gml.append(getPSnode(writer.toString()));
                    } catch (Exception ex) {
                        throw ex;
                    } finally {
                        instream.close();
                    }
                }
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    private String getPSnode(String xml) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = dbf.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
        XPath xPath = XPathFactory.newInstance().newXPath();
        Element result = (Element) xPath.evaluate("FeatureCollection/member/ProtectedSite", doc, XPathConstants.NODE);
        result.setAttribute("xmlns:gml","http://www.opengis.net/gml/3.2");
        result.setAttribute("xmlns:xlink","http://www.w3.org/1999/xlink");
        result.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        return nodeToString(result);
    }

    private String nodeToString(Node node) throws Exception {
        StringWriter buf = new StringWriter();
        buf.getBuffer().append("<gml:featureMember>\n");
        Transformer xform = TransformerFactory.newInstance().newTransformer();
        xform.setOutputProperty(OutputKeys.INDENT, "yes");
        xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        xform.transform(new DOMSource(node), new StreamResult(buf));

        String[] attrToDel = {" xmlns:gml=\"http://www.opengis.net/gml/3.2\"", " xmlns:xlink=\"http://www.w3.org/1999/xlink\"", " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""};
        for (int i = 0; i < attrToDel.length; i++ ) {
            buf.getBuffer().replace(buf.getBuffer().indexOf(attrToDel[i]), buf.getBuffer().indexOf(attrToDel[i])+attrToDel[i].length(), "");
        }

        buf.getBuffer().append("</gml:featureMember>\n");
        return buf.toString();
    }

}
