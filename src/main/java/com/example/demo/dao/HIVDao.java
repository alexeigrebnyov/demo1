package com.example.demo.dao;

import com.example.demo.model.HIV;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HIVDao {
    List<HIV> allHivs();
    List<HIV> allPos(String result);
    void add(HIV hiv);
    void remove(Long id);
    void edit(HIV hiv);
    HIV getById(Long id);
    HIV getHIVByPatientId(String patientId);


}
