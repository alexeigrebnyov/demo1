package com.example.demo.service;

import com.example.demo.dao.HIVDao;
import com.example.demo.dao.HIVDaoImpl;
import com.example.demo.model.HIV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HIVServiceImpl implements HIVService {

    HIVDao hivDao;

    @Autowired
    public void setHivDao(HIVDao hivDao) {
        this.hivDao = hivDao;
    }
    @Override
    public List<HIV> allHivs() {
        return hivDao.allHivs();
    }

    @Override
    public List<HIV> allPos(String result) {
        return hivDao.allPos(result);
    }

    @Override
    public void add(HIV hiv) {
        hivDao.add(hiv);

    }

    @Override
    public void remove(Long id) {
        hivDao.remove(id);

    }

    @Override
    public void edit(HIV hiv) {


    }

    @Override
    public HIV getById(Long id) {
        return hivDao.getById(id);
    }

    @Override
    public HIV getHIVByPatientId(String patientId) {
        return hivDao.getHIVByPatientId(patientId);
    }
}
