package com.example.demo.service;

import com.example.demo.model.HIV;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.util.List;

public interface HIVService  {
    List<HIV> allHivs();
    List<HIV> allPos(String resut);
    void add(HIV hiv);
    void remove(Long id);
    void edit(HIV hiv);
    HIV getById(Long id);
    HIV getHIVByPatientId(String patientId);

}
