package com.example.demo.service;

import com.example.demo.model.HIV;
import com.example.demo.model.UptakeReport;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UptakeService {
    List<UptakeReport> getAll();
    List<UptakeReport> getByDate(Date date);
    List<Object[]> getData(String done, String bio_code) throws SQLException;
    void add(UptakeReport uptakeReport);


}
