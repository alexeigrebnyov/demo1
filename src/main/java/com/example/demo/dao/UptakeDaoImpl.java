package com.example.demo.dao;

import com.example.demo.model.HIV;
import com.example.demo.model.UptakeReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository("UptakeDao")
public class UptakeDaoImpl implements UptakeDao {

    EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional
    @Override
    public List<UptakeReport> getAll() {
        return entityManager.createQuery("select distinct uptake from UptakeReport uptake").getResultList();
    }

    @Override
    public List<UptakeReport> getByDate(Date date) {
        String qery = "select u from UptakeReport u where u.date =:" + date;
        return entityManager.createQuery("select u from UptakeReport u where u.date =: date")
                .setParameter("date", date)
                .getResultList();
    }

    @Transactional
    @Override
    public void add(UptakeReport hiv) {
        entityManager.persist(hiv);

    }
}
