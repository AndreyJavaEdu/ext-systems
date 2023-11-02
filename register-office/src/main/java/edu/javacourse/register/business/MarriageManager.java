package edu.javacourse.register.business;

import edu.javacourse.register.View.MarriageRequest;
import edu.javacourse.register.View.MarriageResponse;
import edu.javacourse.register.dao.MarriageDao;
import edu.javacourse.register.dao.PersonDao;
import edu.javacourse.register.domain.MarriageCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarriageManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageManager.class);
    private MarriageDao marriageDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    public void setMarriageDao(MarriageDao marriageDao) {
        this.marriageDao = marriageDao;
    }

    public MarriageResponse findMarriageCertificate(MarriageRequest request){
        LOGGER.info("findMarriageCertificate called");
        MarriageCertificate certificate = marriageDao.findMarriageCertificate(request);
        return new MarriageResponse();
    }
}
