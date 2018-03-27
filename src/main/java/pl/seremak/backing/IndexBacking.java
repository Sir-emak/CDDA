package pl.seremak.backing;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import pl.seremak.misc.DesignationTypeMapping;
import pl.seremak.misc.Messenger;
import pl.seremak.domain.CDDA;
import pl.seremak.domain.CRFOP;
import pl.seremak.service.CRFOPService;
import pl.seremak.service.GMLService;
import pl.seremak.service.XLSService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class IndexBacking implements Serializable {

    private String editedBy;
    private String institute;
    private UploadedFile uploadedXls;

    private Boolean generated;

    private StreamedContent generatedXls;
    private StreamedContent generatedGml;
    private String generatedXlsFileName;
    private String generatedGmlFileName;
    private Integer datasetId;

    @ManagedProperty("#{xlsService}")
    private XLSService xlsService;
    @ManagedProperty("#{crfopService}")
    private CRFOPService crfopService;
    @ManagedProperty("#{gmlService}")
    private GMLService gmlService;

    @PostConstruct
    public void init() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        editedBy = "KB";
        institute = "GDOS";
        generatedXlsFileName = "CDDA_"+year+"_PL.xls";
        generatedGmlFileName = "CDDA_"+year+"_PL.gml";
        datasetId = 1;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public UploadedFile getUploadedXls() {
        return uploadedXls;
    }

    public void setUploadedXls(UploadedFile file) {
        this.uploadedXls = file;
        try {
            InputStream is = this.uploadedXls.getInputstream();
            xlsService.setXls(new HSSFWorkbook(is));
        } catch (IOException e) {
        }
    }

    public Boolean getGenerated() {
        return generated;
    }

    public void setGenerated(Boolean generated) {
        this.generated = generated;
    }

    public StreamedContent getGeneratedXls() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        xlsService.getXls().write(baos);
        InputStream xlsStream = new ByteArrayInputStream(baos.toByteArray());
        StreamedContent xls = new DefaultStreamedContent(xlsStream, "application/vnd.ms-excel", generatedXlsFileName);
        return xls;
    }

    public void setGeneratedXls(StreamedContent generatedXls) {
        this.generatedXls = generatedXls;
    }

    public StreamedContent getGeneratedGml() throws IOException {
        InputStream is = new ByteArrayInputStream(gmlService.getGml().toString().getBytes());
        StreamedContent gml = new DefaultStreamedContent(is, "application/xml", generatedGmlFileName);
        return gml;
    }

    public void setGeneratedGml(StreamedContent generatedGml) {
        this.generatedGml = generatedGml;
    }

    public String getGeneratedXlsFileName() {
        return generatedXlsFileName;
    }

    public void setGeneratedXlsFileName(String generatedXlsFileName) {
        this.generatedXlsFileName = generatedXlsFileName;
    }

    public String getGeneratedGmlFileName() {
        return generatedGmlFileName;
    }

    public void setGeneratedGmlFileName(String generatedGmlFileName) {
        this.generatedGmlFileName = generatedGmlFileName;
    }

    public void setXlsService(XLSService xlsService) {
        this.xlsService = xlsService;
    }

    public void setCrfopService(CRFOPService crfopService) {
        this.crfopService = crfopService;
    }

    public void setGmlService(GMLService gmlService) {
        this.gmlService = gmlService;
    }

    public void generateRaport() {
        generated = false;
        if (xlsService.getXls() == null) {
            Messenger.fatal("Wybierz plik xls do aktualizacji!");
            return;
        }

        List<CDDA> cddas = xlsService.findAllCDDA();
        List<CRFOP> crfops =  crfopService.findAllCRFOP();

        try {
            gmlService.prepareGML(crfops);
        } catch (Exception e) {
            e.printStackTrace();
            Messenger.fatal("Błąd podczas generowania pliku GML!");
            return;
        }

        for (CDDA cdda: cddas) {
            CRFOP crfop = crfops.stream().filter(cc -> cc.getInspireidlocal().equals(cdda.getPslocalId())).findFirst().orElse(null);
            CDDA updatedCdda = null;
            if (crfop != null) {
                updatedCdda = updateCDDAfromCRFOP(cdda, crfop);
                crfops.remove(crfop);
            } else {
                updatedCdda = updateCDDAfromCRFOP(cdda, null);
            }
            if ((!cdda.equals(updatedCdda)) || (updatedCdda.getSiteEnded().equals(true)))
                xlsService.updateCDDA(updatedCdda);
        }

        for (CRFOP crfop: crfops) {
            CDDA addCdda = updateCDDAfromCRFOP(null, crfop);
            xlsService.addCDDA(addCdda);
        }

        xlsService.setDataset(datasetId, generatedGmlFileName);

        generated = true;
    }

    private CDDA updateCDDAfromCRFOP(CDDA cdda, CRFOP crfop) {
        CDDA newCdda;

        if (cdda == null) {
            newCdda = new CDDA();
            newCdda.setEionetChangeType("A");
            newCdda.setIucnCategory("notAssigned");
            newCdda.setSiteEnded(false);
        } else {
            newCdda = new CDDA(cdda);
            newCdda.setEionetChangeType("U");
        }

        newCdda.setEionetChangeDate(new Date());
        newCdda.setEionetEditedBy(editedBy);
        newCdda.setEionetInstitute(institute);
        if (crfop == null) {
            newCdda.setContainedBy(null);
            newCdda.setSiteEnded(true);
            return newCdda;
        } else {
            newCdda.setSiteEnded(false);
            newCdda.setContainedBy(datasetId);
        }

        newCdda.setNationalId(crfop.getInspireidlocal());
        newCdda.setPslocalId(crfop.getInspireidlocal());
        newCdda.setPsnamespace(crfop.getInspireidns());
        newCdda.setDesignatedAreaType("designatedSite");
        newCdda.setCddaCountryCode("PL");
        newCdda.setCddaRegionCode("PL");
        newCdda.setDesignationTypeCode(DesignationTypeMapping.MAP.get(crfop.getTypformy()));
        newCdda.setSiteArea(crfop.getPowierzchnia());

        if (crfop.getProcentnamorzu().equals(0))
            newCdda.setMajorEcosystemType("terrestrial");
        else if (crfop.getProcentnamorzu().equals(100))
            newCdda.setMajorEcosystemType("marine");
        else
            newCdda.setMajorEcosystemType("marineAndTerrestrial");

        if (!crfop.getProcentnamorzu().equals(0))
            newCdda.setMarineAreaPercentage(crfop.getProcentnamorzu());

        newCdda.setSpatialDataDissemination("public");
        newCdda.setSpatialResolutionCode("scaleLarger100K");

        return newCdda;
    }

}
