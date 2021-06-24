package com.example.demo.model;

public class Analysis {
    private String emc;
    private String fio;
    private String kontengent;
    private String motconsu_resp_id;
    private String upsent;
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

    public String getKontengent() {
        return kontengent;
    }

    public String getMotconsu_resp_id() {
        return motconsu_resp_id;
    }

    public String getUpsent() {
        return upsent;
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

    public void setUpsent(String upsent) {
        this.upsent = upsent;
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

    @Override
    public String toString() {
        return "Analysis{" +
                "emc='" + emc + '\'' +
                ", fio='" + fio + '\'' +
                ", kontengent='" + kontengent + '\'' +
                ", motconsu_resp_id='" + motconsu_resp_id + '\'' +
                ", upsent='" + upsent + '\'' +
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
}
