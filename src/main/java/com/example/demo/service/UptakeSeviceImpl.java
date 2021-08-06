package com.example.demo.service;

import com.example.demo.dao.HIVDao;
import com.example.demo.dao.UptakeDao;
import com.example.demo.model.HIV;
import com.example.demo.model.UptakeReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UptakeSeviceImpl implements UptakeService {

    UptakeDao uptakeDao;

    @Autowired
    public void setUptakeDao(UptakeDao uptakeDao) {
        this.uptakeDao = uptakeDao;
    }
    @Override
    public List<UptakeReport> getAll() {
        return uptakeDao.getAll();
    }

    @Override
    public List<UptakeReport> getByDate(Date date) {
        return uptakeDao.getByDate(date);
    }

    @Override
    public List<Object[]> getData(String done,String bio_code) throws SQLException {
        return uptakeDao.getData(done, bio_code);
    }
    @Override
    public List<Object[]> chek (String done,String bio_code) throws SQLException {
        return uptakeDao.chek(done, bio_code);
    }

    @Override
    public void add(UptakeReport hiv) {
        uptakeDao.add(hiv);

    }

    @Override
    public void saveUser(String name, String password, String role) {
        uptakeDao.saveUser(name, password, role);
    }

}
