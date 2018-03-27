package pl.seremak.domain;

import java.io.Serializable;
import java.util.Date;

public class CRFOP implements Serializable {
    private String inspireidns;
    private String inspireidlocal;
    private Date datautworzenia;
    private String typformy;
    private String nazwaformy;
    private Double powierzchnia;
    private Integer procentnamorzu;

    public String getInspireidns() {
        return inspireidns;
    }

    public void setInspireidns(String inspireidns) {
        this.inspireidns = inspireidns;
    }

    public String getInspireidlocal() {
        return inspireidlocal;
    }

    public void setInspireidlocal(String inspireidlocal) {
        this.inspireidlocal = inspireidlocal;
    }

    public Date getDatautworzenia() {
        return datautworzenia;
    }

    public void setDatautworzenia(Date datautworzenia) {
        this.datautworzenia = datautworzenia;
    }

    public String getTypformy() {
        return typformy;
    }

    public void setTypformy(String typformy) {
        this.typformy = typformy;
    }

    public String getNazwaformy() {
        return nazwaformy;
    }

    public void setNazwaformy(String nazwaformy) {
        this.nazwaformy = nazwaformy;
    }

    public Double getPowierzchnia() {
        return powierzchnia;
    }

    public void setPowierzchnia(Double powierzchnia) {
        this.powierzchnia = powierzchnia;
    }

    public Integer getProcentnamorzu() {
        return procentnamorzu;
    }

    public void setProcentnamorzu(Integer procentnamorzu) {
        this.procentnamorzu = procentnamorzu;
    }

    @Override
    public String toString() {
        return "CRFOP{" +
                "inspireidns='" + inspireidns + '\'' +
                ", inspireidlocal='" + inspireidlocal + '\'' +
                ", datautworzenia=" + datautworzenia +
                ", typformy='" + typformy + '\'' +
                ", nazwaformy='" + nazwaformy + '\'' +
                ", powierzchnia=" + powierzchnia +
                ", procentnamorzu=" + procentnamorzu +
                '}';
    }
}
