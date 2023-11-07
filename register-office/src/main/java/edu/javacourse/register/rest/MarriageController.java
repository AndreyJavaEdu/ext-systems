package edu.javacourse.register.rest;

import edu.javacourse.register.View.MarriageRequest;
import edu.javacourse.register.View.MarriageResponse;
import edu.javacourse.register.business.MarriageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller(value = "controller")
public class MarriageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageController.class);
    @Autowired
    private MarriageManager marriageManager;

    public void setMarriageManager(MarriageManager marriageManager) {
        this.marriageManager = marriageManager;
    }

    public MarriageResponse findMarriageCertificate(MarriageRequest request){
        LOGGER.info("findMarriageCertificate called");
       return marriageManager.findMarriageCertificate(request);
    }

}
