package com.example.demo.model;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hiv")
public class HIV {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patientId")
    private String patientId;

    @Column(name = "uptakeCod")
    private String uptakeCod;

    @Column(name = "result")
    private String result;

    public HIV() {
    }

    public HIV(String patientId, String uptakeCod, String result) {
        this.patientId = patientId;
        this.uptakeCod = uptakeCod;
        this.result = result;
//        this.id = id;
    }


    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getUptakeCod() {
        return uptakeCod;
    }

    public void setUptakeCod(String uptakeCod) {
        this.uptakeCod = uptakeCod;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setId(Long id) {
        this.id = id;
    }

      public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "HIV{" +
                "id=" + id +
                ", patientId='" + patientId + '\'' +
                ", uptakeCod='" + uptakeCod + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
