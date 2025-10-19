package com.example.publictransportsystem.repository;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named("baseRepositoryJPA")
@Dependent
public class BaseRepositoryJPA {

    @PersistenceContext
    protected EntityManager entityManager;

    public void isDBConnectionHealthy() throws Exception {
        entityManager.createNativeQuery("SELECT 1").getSingleResult();
    }
}
