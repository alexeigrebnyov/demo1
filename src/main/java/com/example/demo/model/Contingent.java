package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contingent")
public class Contingent {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emc")
    private Integer emc;

    @Column(name = "code")
    private  Integer code;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    private Integer refer;

    public Contingent(Long id, Integer emc, Integer code, String name, String sex) {
        this.id = id;
        this.emc = emc;
        this.code = code;
        this.name = name;
        this.sex = sex;
    }

    public Contingent() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public Integer getEmc() {
        return emc;
    }

    public void setEmc(Integer emc) {
        this.emc = emc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getRefer() {
        return refer;
    }

    public void setRefer(Integer refer) {
        this.refer = refer;
    }

    @Override
    public String toString() {
        return "Contingent{" +
                "id=" + id +
                ", emc=" + emc +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
