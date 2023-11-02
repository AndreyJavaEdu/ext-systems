package edu.javacourse.register.dao;

import edu.javacourse.register.View.MarriageRequest;
import edu.javacourse.register.domain.MarriageCertificate;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MarriageDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageDao.class);
    private EntityManager entityManager;
@Value("${test.value}")
    private String test;

    public void setTest(String test) {
        this.test = test;
    }

    public MarriageCertificate findMarriageCertificate(MarriageRequest request){
        LOGGER.info("findMarriageCertificate called:{}",test);

        return null;
    }
}