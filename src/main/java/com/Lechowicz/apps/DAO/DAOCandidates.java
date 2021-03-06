package com.Lechowicz.apps.DAO;

import com.Lechowicz.apps.persons.Candidate;
import com.Lechowicz.apps.persons.Person;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DAOCandidates extends DAOsql implements InterfaceDAO {
    private List<Person> candidates;

    public DAOCandidates() throws IOException, SQLException {
        super();
        candidates = new ArrayList<>();
        fillList();
    }


    @Override
    public List<Person> getPersons() {
        return candidates;
    }

    @Override
    public void updatePerson(String fullName, Person person) {

        for(int index = 0; index < candidates.size(); index++){
            if(fullName.equals(candidates.get(index).getFullName())){
                candidates.remove(candidates.get(index));
                boolean add = candidates.add(person);
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
        candidates.removeIf(singlePerson -> person == singlePerson);

        try {
            updateDataBase();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPerson(String[] personData, Boolean isMentor) {
        Candidate newCandidate = new Candidate(personData);
        for (Person person : candidates) {
            Candidate singleCandidate = (Candidate) person;
            if (newCandidate.getApplicationCode().equals((singleCandidate.getApplicationCode()))) {
                return;
            }
        }
        candidates.add(newCandidate);

        try {
            updateDataBase();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void fillList() throws SQLException {
        int lengthOfCandidate = 6;
        String[] params = new String[lengthOfCandidate];

        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = con.prepareStatement("SELECT * FROM applicants");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            for(int index = 0; index < lengthOfCandidate;index++){
                params[index] = rs.getString(index + 1);
            }
            candidates.add(new Candidate(params));
        }

        con.close();
    }

    protected void updateDataBase() throws SQLException, FileNotFoundException {
        Connection con = DriverManager.getConnection(url, user, password);
        ScriptRunner sr = new ScriptRunner(con);


        Reader reader = new BufferedReader(new FileReader("src/main/resources/clear_applicants.sql"));

        sr.runScript(reader);
        con.close();

        con = DriverManager.getConnection(url, user, password);

        for(Person person: candidates){
            Candidate can = (Candidate) person;
            String query = "INSERT INTO applicants(id, first_name,last_name, phone_number, email, application_code) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, can.getId());
            pst.setString(2, can.getFirstName());
            pst.setString(3, can.getLastName());
            pst.setString(4, can.getPhoneNumber());
            pst.setString(5, can.getEmail());
            pst.setInt(6, can.getApplicationCode());

            pst.executeUpdate();
        }
        con.close();
    }
}
