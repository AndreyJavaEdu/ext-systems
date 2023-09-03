package edu.javapetproject.city.web;

import edu.javapetproject.city.dao.PersonCheckDao;
import edu.javapetproject.city.domain.PersonRequest;
import edu.javapetproject.city.domain.PersonResponse;
import edu.javapetproject.city.exception.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "CheckPersonServlet123", urlPatterns = {"/checkperson"})
public class CheckPersonServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CheckPersonServlet.class);
    private PersonCheckDao dao;

    @Override
    public void init() throws ServletException {
        logger.info("Servlet is created");
        dao = new PersonCheckDao();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String surname = req.getParameter("Surname");
        PersonRequest pr = new PersonRequest();
        pr.setSurname(surname);
        pr.setGivenName("Павел");
        pr.setPatronomic("Николаевич");
        pr.setDateOfBirth(LocalDate.of(1995,3,18));
        pr.setStreetCode(1);
        pr.setBuilding("5");
        pr.setExtension("1");
        pr.setApartment("84");
                try {
            PersonResponse ps = dao.checkPerson(pr);
            if (ps.isRegistered()==true){
                resp.getWriter().write("Registered");
            }else resp.getWriter().write("Not registered");
        } catch (PersonCheckException e) {
            throw new RuntimeException(e);
        }
    }
}
