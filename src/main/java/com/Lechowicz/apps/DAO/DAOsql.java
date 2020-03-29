package com.Lechowicz.apps.DAO;

import com.Lechowicz.apps.persons.Candidate;
import com.Lechowicz.apps.persons.Mentor;
import com.Lechowicz.apps.persons.Person;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOsql implements InterfaceDAO {
    private List<Person> mentors;
    private List<Person> candidates;

    private String url;
    private String user;
    private String password;
    private String[] params = new String[8];
    private Integer lengthOfCandidate = 6;
    private Integer lengthOfMentors = 8;

    public DAOsql() throws IOException, SQLException{
        Properties prop = readProperties("src/main/resources/database.properties");
        url = prop.getProperty("db.url");
        user = prop.getProperty("db.user");
        password = prop.getProperty("db.passwd");

        mentors = new ArrayList<>();
        candidates = new ArrayList<>();
        openDB();
    }

    public static Properties readProperties(String path) throws IOException {

        Properties props = new Properties();
        Path myPath = Paths.get(path);

        BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);
        props.load(bf);

        return props;
    }


    private void fillLists(Connection con) throws SQLException {
        PreparedStatement pst = con.prepareStatement("SELECT * FROM applicants");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            for(int index = 0; index < lengthOfCandidate;index++){
                params[index] = rs.getString(index + 1);
            }
            candidates.add(new Candidate(params));
        }

        pst = con.prepareStatement("SELECT * FROM mentors");
        rs = pst.executeQuery();

        while (rs.next()) {
            for(int index = 0; index < lengthOfMentors;){
                params[index] = rs.getString(index + 1);
                index++;
            }
            mentors.add(new Mentor(params));
        }
    }

    private void openDB() throws SQLException{
        Connection con = DriverManager.getConnection(url, user, password);
        fillLists(con);
    }

    @Override
    public List<Person> getMentors() {
        return mentors;
    }

    @Override
    public List<Person> getCandidates() {
        return candidates;
    }

    @Override
    public void updatePerson(String fullName, Person person) {
        List<Person> searchList;
        if(person instanceof Mentor){
          searchList = mentors;
        }
        else{
            searchList = candidates;
        }
        for(Person singlePerson: searchList){
            if(fullName.equals(singlePerson.getFullName())){
                searchList.remove(singlePerson);
                searchList.add(person);
            }
        }
    }

    @Override
    public void deletePerson(Person person) {
        List<Person> searchList;
        if(person instanceof Mentor){
            searchList = mentors;
        }
        else{
            searchList = candidates;
        }
        searchList.removeIf(singlePerson -> person == singlePerson);
    }

    @Override
    public void addPerson(String[] personData, Boolean isMentor) {
        if(isMentor){
            mentors.add(new Mentor(personData));

        }
        else{
            candidates.add(new Candidate(personData));
        }
    }

    @Override
    public Candidate getCandidateByCode(Integer appCode) {
        for(Person person : candidates){
            Candidate candidate = (Candidate) person;
            if(candidate.getApplicationCode().equals(appCode)){
                return candidate;
            }
        }

        return null;
    }

    @Override
    public Candidate getCandidateByFullName(String fullName) {
        for(Person person : candidates){
            if(person.getFullName().equals(fullName)){
                return (Candidate) person;
            }
        }
        return null;
    }

}
