package com.example.publictransportsystem.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseRepositoryJPA {

    @PersistenceContext
    protected EntityManager entityManager;
}
