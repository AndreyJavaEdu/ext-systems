package edu.javapetproject.city.web;

import edu.javapetproject.city.dao.PersonCheckDao;
import edu.javapetproject.city.dao.PoolConnectionBuilder;
import edu.javapetproject.city.domain.PersonRequest;
import edu.javapetproject.city.domain.PersonResponse;
import edu.javapetproject.city.exception.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

@Path("/check")
@Singleton
public class CheckPersonService {
    private static final Logger logger = LoggerFactory.getLogger(CheckPersonService.class);
    private PersonCheckDao dao;
    @PostConstruct
    public void init(){
        logger.info("SERVICE is created");
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        logger.info(request.toString());
        return dao.checkPerson(request);
    }

}
