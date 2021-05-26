package com.example.demo.service;

import com.example.demo.model.Contingent;
import com.example.demo.model.HIV;

import java.util.List;

public interface ContService {
    List<Contingent> getAll();
    Contingent getByEMC(Integer emc);
    Contingent getByCode(Integer code);
    void add(Contingent contingent);


}
