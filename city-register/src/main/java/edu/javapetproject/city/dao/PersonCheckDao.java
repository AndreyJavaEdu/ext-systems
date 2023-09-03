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
                    "CURRENT_DATE >= ap.start_date and (CURRENT_DATE <= ap.end_date or ap.end_date is null) "+
                    "and upper(p.surname)=upper(?) " +
                    "and upper(p.given_name)=upper(?) " +
                    "and upper(p.patronomyc)=upper(?) " +
                    "and p.date_of_birth=? " +
                    "and a.street_code=? " +
                    "and upper(a.building)=upper(?) " ;

    public PersonCheckDao() {
        try{
            Class.forName("org.postgresql.Driver");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();

        String sql = SQL_REQUEST;
        if(request.getExtension() != null){
            sql += "and upper(a.extension)=upper(?) ";
        }else {
            sql += "and a.extension is null ";
        }
        if(request.getApartment() !=null){
            sql+="and upper(a.apartment)=upper(?); ";
        }else {
            sql += "and a.apartment is null ";
        }

        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){

            int count =1;
            stmt.setString(count++, request.getSurname());
            stmt.setString(count++, request.getGivenName());
            stmt.setString(count++, request.getPatronomic());
            stmt.setDate(count++, java.sql.Date.valueOf(request.getDateOfBirth()));
            stmt.setInt(count++, request.getStreetCode());
            stmt.setString(count++, request.getBuilding());
            if(request.getExtension() != null) {
                stmt.setString(count++, request.getExtension());
            }
            if(request.getApartment() != null) {
                stmt.setString(count++, request.getApartment());
            }

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
