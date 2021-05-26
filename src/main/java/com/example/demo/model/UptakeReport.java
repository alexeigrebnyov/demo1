package com.example.demo.model;

import org.apache.batik.ext.awt.image.SVGComposite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "uptake")
public class UptakeReport {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emc")
    private Integer emc;

    @Column(name = "date")
    private Date date;

//    @Column(name = "refered")
//    private Integer refer;

//    @Column(name = "emc")
//    private Integer emc;
//
//    @Column(name = "code")
//    private Integer code;

    @OneToOne(fetch = FetchType.EAGER)
    private Contingent contingent;


    public UptakeReport() {
    }

    public UptakeReport(Integer emc,Date date,
                        Contingent contingent) {
        this.emc = emc;
        this.date = date;
        this.contingent = contingent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Integer getRefer() {
//        return refer;
//    }
//
//    public void setRefer(Integer refer) {
//        this.refer = refer;
//    }

    public Contingent getContingent() {
        return contingent;
    }

    public void setContingent(Contingent contingent) {
        this.contingent = contingent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getEmc() {
        return emc;
    }

    public void setEmc(Integer emc) {
        this.emc = emc;
    }
//
//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }


    @Override
    public String toString() {
        return "UptakeReport{" +
                "id=" + id +
                ", emc=" + emc +
                ", date=" + date +
//                ", refer=" + refer +
                ", contingent=" + contingent +
                '}';
    }
}
