package com.example.demo.dao;

import com.example.demo.model.HIV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository("HIVDao")
public class HIVDaoImpl implements HIVDao {

    EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional
    @Override
    public List<HIV> allHivs() {
        return entityManager.createQuery("select hiv from HIV hiv").getResultList();
    }

    @Override
    public List<HIV> allPos(String result) {
        return entityManager.createQuery("select hiv from HIV hiv where hiv.result =: result")
                .setParameter("result", result)
                .getResultList();
    }

    @Transactional
    @Override
    public void add(HIV hiv) {
        entityManager.persist(hiv);

    }
    @Transactional
    @Override
    public void remove(Long id) {
        entityManager.remove(getById(id));

    }
    @Transactional
    @Override
    public void edit(HIV hiv) {
        entityManager.merge(hiv);

    }
    @Transactional
    @Override
    public HIV getById(Long id) {
       return entityManager.find(HIV.class, id);
    }
    @Transactional
    @Override
    public HIV getHIVByPatientId(String patientId) {
        return (HIV)entityManager.createQuery("select hiv from HIV hiv where hiv.patientId =: patientId ")
                .setParameter("patientId", patientId)
                .getSingleResult();
    }
}
