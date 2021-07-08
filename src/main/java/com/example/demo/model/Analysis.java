package com.example.demo.model;

import java.util.Objects;

public class Analysis {
    private String emc;
    private String fio;
    private String kontengent;
    private String motconsu_resp_id;
    private String resultHiv;
    private String resultHbsAg;
    private String resultatHCV;
    private String resultSyphIfa;
    private String resultMRP;
    private String main_org_id;
    private String label;
    private String patdirect_id;
    private String date_bio;
    private String hiv;
    private String hbsAg;
    private String atHCV;
    private String syphIFA;
    private String syphMRP;
    private String code;

    public Analysis() {
    }

    public Analysis(String emc, String fio, String kontengent, String motconsu_resp_id, String resultHiv, String resultHbsAg, String resultatHCV, String resultSyphIfa, String resultMRP, String main_org_id, String label, String patdirect_id, String date_bio, String hiv, String hbsAg, String atHCV, String syphIFA, String syphMRP, String code) {
        this.emc = emc;
        this.fio = fio;
        this.kontengent = kontengent;
        this.motconsu_resp_id = motconsu_resp_id;
        this.resultHiv = resultHiv;
        this.resultHbsAg = resultHbsAg;
        this.resultatHCV = resultatHCV;
        this.resultSyphIfa = resultSyphIfa;
        this.resultMRP = resultMRP;
        this.main_org_id = main_org_id;
        this.label = label;
        this.patdirect_id = patdirect_id;
        this.date_bio = date_bio;
        this.hiv = hiv;
        this.hbsAg = hbsAg;
        this.atHCV = atHCV;
        this.syphIFA = syphIFA;
        this.syphMRP = syphMRP;
        this.code = code;
    }

    public String getKontengent() {
        return kontengent;
    }

    public String getMotconsu_resp_id() {
        return motconsu_resp_id;
    }


    public String getMain_org_id() {
        return main_org_id;
    }

    public String getLabel() {
        return label;
    }

    public String getPatdirect_id() {
        return patdirect_id;
    }

    public String getDate_bio() {
        return date_bio;
    }

    public String getEmc() {
        return emc;
    }

    public String getFio() {
        return fio;
    }


    public String getCode() {
        return code;
    }

    public String getHiv() {
        return hiv;
    }

    public void setHiv(String hiv) {
        this.hiv = hiv;
    }

    public String getHbsAg() {
        return hbsAg;
    }

    public void setHbsAg(String hbsAg) {
        this.hbsAg = hbsAg;
    }

    public String getAtHCV() {
        return atHCV;
    }

    public void setAtHCV(String atHCV) {
        this.atHCV = atHCV;
    }

    public String getSyphIFA() {
        return syphIFA;
    }

    public void setSyphIFA(String syphIFA) {
        this.syphIFA = syphIFA;
    }

    public String getSyphMRP() {
        return syphMRP;
    }

    public void setSyphMRP(String syphMRP) {
        this.syphMRP = syphMRP;
    }

    public void setEmc(String emc) {
        this.emc = emc;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setKontengent(String kontengent) {
        this.kontengent = kontengent;
    }

    public void setMotconsu_resp_id(String motconsu_resp_id) {
        this.motconsu_resp_id = motconsu_resp_id;
    }


    public void setMain_org_id(String main_org_id) {
        this.main_org_id = main_org_id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPatdirect_id(String patdirect_id) {
        this.patdirect_id = patdirect_id;
    }

    public void setDate_bio(String date_bio) {
        this.date_bio = date_bio;
    }

    public String getResultHiv() {
        return resultHiv;
    }

    public void setResultHiv(String resultHiv) {
        this.resultHiv = resultHiv;
    }

    public String getResultHbsAg() {
        return resultHbsAg;
    }

    public void setResultHbsAg(String resultHbsAg) {
        this.resultHbsAg = resultHbsAg;
    }

    public String getResultatHCV() {
        return resultatHCV;
    }

    public void setResultatHCV(String resultatHCV) {
        this.resultatHCV = resultatHCV;
    }

    public String getResultSyphIfa() {
        return resultSyphIfa;
    }

    public void setResultSyphIfa(String resultSyphIfa) {
        this.resultSyphIfa = resultSyphIfa;
    }

    public String getResultMRP() {
        return resultMRP;
    }

    public void setResultMRP(String resultMRP) {
        this.resultMRP = resultMRP;
    }

    @Override
    public String toString() {
        return "Analysis{" +
                "emc='" + emc + '\'' +
                ", fio='" + fio + '\'' +
                ", kontengent='" + kontengent + '\'' +
                ", motconsu_resp_id='" + motconsu_resp_id + '\'' +
                ", resultHiv='" + resultHiv + '\'' +
                ", resultHbsAg='" + resultHbsAg + '\'' +
                ", resultatHCV='" + resultatHCV + '\'' +
                ", resultSyphIfa='" + resultSyphIfa + '\'' +
                ", resultMRP='" + resultMRP + '\'' +
                ", main_org_id='" + main_org_id + '\'' +
                ", label='" + label + '\'' +
                ", patdirect_id='" + patdirect_id + '\'' +
                ", date_bio='" + date_bio + '\'' +
                ", hiv='" + hiv + '\'' +
                ", hbsAg='" + hbsAg + '\'' +
                ", atHCV='" + atHCV + '\'' +
                ", syphIFA='" + syphIFA + '\'' +
                ", syphMRP='" + syphMRP + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Analysis analysis = (Analysis) o;
        return Objects.equals(getEmc(), analysis.getEmc()) &&
                Objects.equals(getFio(), analysis.getFio()) &&
                Objects.equals(getKontengent(), analysis.getKontengent()) &&
                Objects.equals(getMotconsu_resp_id(), analysis.getMotconsu_resp_id()) &&
                Objects.equals(getResultHiv(), analysis.getResultHiv()) &&
                Objects.equals(getResultHbsAg(), analysis.getResultHbsAg()) &&
                Objects.equals(getResultatHCV(), analysis.getResultatHCV()) &&
                Objects.equals(getResultSyphIfa(), analysis.getResultSyphIfa()) &&
                Objects.equals(getResultMRP(), analysis.getResultMRP()) &&
                Objects.equals(getMain_org_id(), analysis.getMain_org_id()) &&
                Objects.equals(getLabel(), analysis.getLabel()) &&
                Objects.equals(getPatdirect_id(), analysis.getPatdirect_id()) &&
                Objects.equals(getDate_bio(), analysis.getDate_bio()) &&
                Objects.equals(getHiv(), analysis.getHiv()) &&
                Objects.equals(getHbsAg(), analysis.getHbsAg()) &&
                Objects.equals(getAtHCV(), analysis.getAtHCV()) &&
                Objects.equals(getSyphIFA(), analysis.getSyphIFA()) &&
                Objects.equals(getSyphMRP(), analysis.getSyphMRP()) &&
                Objects.equals(getCode(), analysis.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmc(), getFio(), getKontengent(), getMotconsu_resp_id(), getResultHiv(), getResultHbsAg(), getResultatHCV(), getResultSyphIfa(), getResultMRP(), getMain_org_id(), getLabel(), getPatdirect_id(), getDate_bio(), getHiv(), getHbsAg(), getAtHCV(), getSyphIFA(), getSyphMRP(), getCode());
    }

    public boolean chekHiv() {
        if (hiv.equals("1")){
        try {
            return resultHiv != null;
        } catch (Exception ex) {return  false;}}
       return true;
    }
    public boolean chekHbs() {
        if (hbsAg.equals("1")){
        try {
            return resultHbsAg != null;
        } catch (Exception ex) {return  false;}}
       return true;
    }
    public boolean chekatHCV() {
        if (atHCV.equals("1")){
        try {
            return resultatHCV != null;
        } catch (Exception ex) {return false;}}
       return true;
    }
    public boolean chekSyphIfa() {
        if (syphIFA.equals("1")){
        try {
            return resultSyphIfa != null;
        } catch (Exception ex) {return  false;}}
        return true;
    }
    public boolean chek() {
        if (syphMRP.equals("1")) {
            try {
                return resultMRP != null;
            } catch (Exception ex) {return false;}
        }
        return true;

    }
    public String totalChek() {
        if(chek() && chekatHCV() && chekHbs() && chekHiv() && chekSyphIfa()) {return " ";}
        return "!!!!!";
    }
}
