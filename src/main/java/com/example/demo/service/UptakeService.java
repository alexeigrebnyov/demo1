package com.example.demo.service;

import com.example.demo.model.HIV;
import com.example.demo.model.UptakeReport;

import java.util.Date;
import java.util.List;

public interface UptakeService {
    List<UptakeReport> getAll();
    List<UptakeReport> getByDate(Date date);
    void add(UptakeReport uptakeReport);


}
