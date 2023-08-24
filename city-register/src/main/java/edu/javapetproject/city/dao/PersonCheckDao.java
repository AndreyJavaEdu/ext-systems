package edu.javapetproject.city.dao;

import edu.javapetproject.city.domain.PersonRequest;
import edu.javapetproject.city.domain.PersonResponse;
import edu.javapetproject.city.exception.PersonCheckException;

import java.sql.*;

public class PersonCheckDao {
    private static final String SQL_REQUEST =
            "SELECT temporal FROM cr_address_person ap " +
                    "join cr_person p on (ap.person_id=p.person_id) " +
                    "join cr_address a on (ap.address_id=a.address_id) " +
                    "WHERE " +
                    "upper(p.surname)=upper(?) " +
                    "and upper(p.given_name)=upper(?) " +
                    "and upper(p.patronomyc)=upper(?) " +
                    "and p.date_of_birth=? " +
                    "and a.street_code=? " +
                    "and upper(a.building)=upper(?) " +
                    "and upper(a.extension)=upper(?) " +
                    "and upper(a.apartment)=upper(?); ";
    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();
        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_REQUEST)){
            stmt.setString(1, request.getSurname());
            stmt.setString(2, request.getGivenName());
            stmt.setString(3, request.getPatronomic());
            stmt.setDate(4, java.sql.Date.valueOf(request.getDateOfBirth()));
            stmt.setInt(5, request.getStreetCode());
            stmt.setString(6, request.getBuilding());
            stmt.setString(7, request.getExtension());
            stmt.setString(8, request.getApartment());


            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                response.setRegistered(true);
                response.setTemporal(rs.getBoolean("temporal"));
            }

        }catch (SQLException ex){
            throw new PersonCheckException(ex);
        }
        return response;

    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost/city-register",
                "postgres", "postgres");
    }
}
