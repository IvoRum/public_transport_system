package com.example.publictransportsystem.repository;

import com.example.publictransportsystem.persitence.PassengerEntity;

import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Dependent
public class PassengerRepository extends BaseRepositoryJPA{

    public  Optional<PassengerEntity> findPassengerByName(final String personName) {
        final TypedQuery<PassengerEntity> query = entityManager.createQuery(
                "SELECT p FROM PassengerEntity p WHERE p.name = :name",
                PassengerEntity.class
        );
        query.setParameter("name", personName);
        final List<PassengerEntity> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.get(0));
        }
    }
}
