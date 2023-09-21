package edu.javacourse.register.dao;

import edu.javacourse.register.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class PersonDao {
    private EntityManager entityManager;

    public PersonDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Persistence");
        entityManager = factory.createEntityManager();
    }

    public List<Person> findPersons () {
        Query query = entityManager.createNamedQuery("Person.findPersons");
        Query personId = query.setParameter("personId", 3L);
        List resultList = query.getResultList();
        return  resultList;
    }
}
