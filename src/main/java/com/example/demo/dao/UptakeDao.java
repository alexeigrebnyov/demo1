package com.example.demo.dao;

import com.example.demo.model.HIV;
import com.example.demo.model.UptakeReport;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UptakeDao {
    List<UptakeReport> getAll();
    List<UptakeReport> getByDate (Date date);
    void add(UptakeReport uptakeReport);
    List<Object[]> getData(String bio_code) throws SQLException;

}
