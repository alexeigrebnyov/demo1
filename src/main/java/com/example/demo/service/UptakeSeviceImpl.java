package com.example.demo.service;

import com.example.demo.dao.HIVDao;
import com.example.demo.dao.UptakeDao;
import com.example.demo.model.HIV;
import com.example.demo.model.UptakeReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public void add(UptakeReport hiv) {
        uptakeDao.add(hiv);

    }

}
