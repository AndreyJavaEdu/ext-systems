package edu.javapetproject.city.web;

import edu.javapetproject.city.dao.PersonCheckDao;
import edu.javapetproject.city.dao.PoolConnectionBuilder;
import edu.javapetproject.city.domain.PersonRequest;
import edu.javapetproject.city.domain.PersonResponse;
import edu.javapetproject.city.exception.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "CheckPersonServlet123", urlPatterns = {"/checkperson"})
public class CheckPersonServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CheckPersonServlet.class);
    private PersonCheckDao dao;

    @Override
    public void init() throws ServletException {
        logger.info("Servlet is created");
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        PersonRequest pr = new PersonRequest();
        pr.setSurname(req.getParameter("surname"));
        pr.setGivenName(req.getParameter("givenname"));
        pr.setPatronomic(req.getParameter("patronymic"));
        LocalDate dateOfBirth = LocalDate.parse(req.getParameter("DateOfBirth"), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        pr.setDateOfBirth(dateOfBirth);
        pr.setStreetCode(Integer.parseInt(req.getParameter("streetCode")));
        pr.setBuilding(req.getParameter("building"));
        pr.setExtension(req.getParameter("extension"));
        pr.setApartment(req.getParameter("apartment"));
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
