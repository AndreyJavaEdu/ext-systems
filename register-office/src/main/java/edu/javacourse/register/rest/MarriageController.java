package edu.javacourse.register.rest;

import edu.javacourse.register.View.MarriageRequest;
import edu.javacourse.register.View.MarriageResponse;
import edu.javacourse.register.business.MarriageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Controller(value = "controller")
@Path("/mc")
public class MarriageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageController.class);
    @Autowired
    @Qualifier("marriageService")
    private MarriageManager marriageManager;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MarriageResponse findMarriageCertificate(){
        LOGGER.info("findMarriageCertificate called");
       return marriageManager.findMarriageCertificate(null);
    }

}
