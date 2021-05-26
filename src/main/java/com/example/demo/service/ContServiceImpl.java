package com.example.demo.service;

import com.example.demo.dao.ContDao;
import com.example.demo.dao.UptakeDao;
import com.example.demo.model.Contingent;
import com.example.demo.model.UptakeReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContServiceImpl implements ContService {

   ContDao contDao;

    @Autowired
    public void setUptakeDao(ContDao contDao) {
        this.contDao = contDao;
    }
    @Override
    public List<Contingent> getAll() {
        return contDao.getAll();
    }

    @Override
    public Contingent getByEMC(Integer emc) {
        return contDao.getByEMC(emc);
    }

    @Override
    public Contingent getByCode(Integer code) {
        return  contDao.getByCode(code);   }

    @Override
    public void add(Contingent contingent) {
        contDao.add(contingent);

    }

}
