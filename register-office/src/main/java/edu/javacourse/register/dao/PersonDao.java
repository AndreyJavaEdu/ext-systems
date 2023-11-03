package edu.javacourse.register.dao;

import edu.javacourse.register.domain.Person;


import javax.persistence.*;
import java.util.List;

public class PersonDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> findPersons () {
        Query query = entityManager.createNamedQuery("Person.findPersons");
        Query personId = query.setParameter("personId", 3L);
        List resultList = query.getResultList();
        return  resultList;
    }
}
