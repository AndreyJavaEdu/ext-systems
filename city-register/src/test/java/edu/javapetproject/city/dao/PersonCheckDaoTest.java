package edu.javapetproject.city.dao;


import edu.javapetproject.city.domain.PersonRequest;
import edu.javapetproject.city.domain.PersonResponse;
import edu.javapetproject.city.exception.PersonCheckException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class PersonCheckDaoTest {

@Test
    public void checkPerson() throws PersonCheckException {
    PersonRequest pr = new PersonRequest();
    pr.setSurname("Васильев");
    pr.setGivenName("Павел");
    pr.setPatronomic("Николаевич");
    pr.setDateOfBirth(LocalDate.of(1995,3,18));
    pr.setStreetCode(1);
    pr.setBuilding("5");
    pr.setExtension("1");
    pr.setApartment("84");

    PersonCheckDao dao = new PersonCheckDao();
    PersonResponse ps = dao.checkPerson(pr);
    Assert.assertTrue(ps.isRegistered());
    Assert.assertFalse(ps.isTemporal());
    }
}