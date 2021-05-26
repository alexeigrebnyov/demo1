package com.example.demo.dao;

import com.example.demo.model.Contingent;
import com.example.demo.model.UptakeReport;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContDao {
    List<Contingent> getAll();
    Contingent getByEMC(Integer emc);
    Contingent getByCode(Integer code);
    void add(Contingent contingent);

}
