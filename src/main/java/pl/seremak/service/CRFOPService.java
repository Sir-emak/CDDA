package pl.seremak.service;

import pl.seremak.domain.CRFOP;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "crfopService")
@ViewScoped
public class CRFOPService implements Serializable {

    private DataSource ds;

    @PostConstruct
    public void init() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/geoserwisDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public List<CRFOP> findAllCRFOP() {
        List<CRFOP> crfopList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT inspireidns, inspireidlocal, datautworzenia, typformy, nazwaformy, powierzchnia, procentnamorzu FROM cdda";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                CRFOP crfop = new CRFOP();
                crfop.setInspireidns(rs.getString("inspireidns"));
                crfop.setInspireidlocal(rs.getString("inspireidlocal"));
                crfop.setDatautworzenia(rs.getDate("datautworzenia"));
                crfop.setTypformy(rs.getString("typformy"));
                crfop.setNazwaformy(rs.getString("nazwaformy"));
                crfop.setPowierzchnia(rs.getDouble("powierzchnia"));
                crfop.setProcentnamorzu(rs.getInt("procentnamorzu"));

                crfopList.add(crfop);
            }

            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

        return crfopList;
    }

}
