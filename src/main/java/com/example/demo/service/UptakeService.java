package com.example.demo.service;

import com.example.demo.model.HIV;
import com.example.demo.model.UptakeReport;
import com.example.demo.model.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UptakeService {
    List<UptakeReport> getAll();
    List<UptakeReport> getByDate(Date date);
    List<Object[]> getData(String done, String bio_code) throws SQLException;
    List<Object[]> chek (String done, String bio_code) throws SQLException;
    void add(UptakeReport uptakeReport);
    void saveUser(String name, String password, String role);
    public List<User> getAllUsers();
    public void removeUserById(long id);


}
