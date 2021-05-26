package com.example.demo.dao;

import com.example.demo.model.Contingent;
import com.example.demo.model.UptakeReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository("ContDao")
public class ContDaoImpl implements ContDao {

    EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional
    @Override
    public List<Contingent> getAll() {
        return entityManager.createQuery("select cont from Contingent cont").getResultList();
    }

    @Override
    public Contingent getByEMC(Integer emc) {
//        String query = "FROM Continge user WHERE user.car.model = '" + model + "'"
//                + "AND user.car.series = " + series;
        return (Contingent) entityManager.createQuery("select cont from Contingent cont where cont.emc =:emc")
                .setParameter("emc", emc)
//                .setParameter("codec", code)
                .getSingleResult();
    }

    @Transactional
    @Override
    public Contingent getByCode(Integer code) {
        return (Contingent) entityManager.createQuery("select cont from Contingent cont where cont.code =:code")
                .setParameter("code", code)
//                .setParameter("codec", code)
                .getSingleResult();
    }

    @Transactional
    @Override
    public void add(Contingent contingent) {
        entityManager.persist(contingent);

    }
}
