package com.example.demo.dao;

import com.example.demo.model.HIV;
import com.example.demo.model.UptakeReport;
import com.example.demo.model.User;
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
    List<Object[]> getData(String done,String bio_code) throws SQLException;
    List<Object[]> chek (String done,String bio_code) throws SQLException;
    User loadUserByUsername(String s);
    void saveUser(String name, String password, String role);

}
