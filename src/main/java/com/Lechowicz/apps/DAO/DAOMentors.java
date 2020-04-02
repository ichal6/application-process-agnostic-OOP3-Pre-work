package com.Lechowicz.apps.DAO;

import com.Lechowicz.apps.persons.Mentor;
import com.Lechowicz.apps.persons.Person;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOMentors extends DAOsql implements InterfaceDAO {
    private List<Person> mentors;

    public DAOMentors() throws IOException, SQLException {
        super();
        mentors = new ArrayList<>();
        fillList();
    }

    @Override
    public List<Person> getPersons() {
        return mentors;
    }

    @Override
    public void updatePerson(String fullName, Person person) {

        for(int index = 0; index < mentors.size(); index++){
            if(fullName.equals(mentors.get(index).getFullName())){
                mentors.remove(mentors.get(index));
                mentors.add(person);
                break;
            }
        }

        try {
            updateDataBase();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deletePerson(Person person) {
        mentors.removeIf(singlePerson -> person == singlePerson);

        try {
            updateDataBase();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPerson(String[] personData, Boolean isMentor) {
        mentors.add(new Mentor(personData));

        try {
            updateDataBase();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void fillList() throws SQLException {
        int lengthOfMentors = 8;
        String[] params = new String[lengthOfMentors];

        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = con.prepareStatement("SELECT * FROM mentors");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            for(int index = 0; index < lengthOfMentors;){
                params[index] = rs.getString(index + 1);
                index++;
            }
            mentors.add(new Mentor(params));
        }
        con.close();
    }

    protected void updateDataBase() throws SQLException, FileNotFoundException {
        Connection con = DriverManager.getConnection(url, user, password);
        ScriptRunner sr = new ScriptRunner(con);

        Reader reader = new BufferedReader(new FileReader("src/main/resources/clear_mentors.sql"));

        sr.runScript(reader);
        con.close();

        con = DriverManager.getConnection(url, user, password);

        for(Person person: mentors){
            Mentor men = (Mentor) person;
            String query = "INSERT INTO mentors(id, first_name,last_name, nick_name, phone_number, email, city, favourite_number) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, men.getId());
            pst.setString(2, men.getFirstName());
            pst.setString(3, men.getLastName());
            pst.setString(4, men.getNickName());
            pst.setString(5, men.getPhoneNumber());
            pst.setString(6, men.getEmail());
            pst.setString(7, men.getCity());
            if(men.getFavouriteNumber() != null){
                pst.setInt(8, men.getFavouriteNumber());
            }else{
                pst.setNull(8, java.sql.Types.NULL);
            }
            pst.executeUpdate();
        }
        con.close();
    }
}
