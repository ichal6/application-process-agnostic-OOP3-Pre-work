package com.Lechowicz.apps.DAO;

import com.Lechowicz.apps.persons.Candidate;
import com.Lechowicz.apps.persons.Mentor;
import com.Lechowicz.apps.persons.Person;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


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

    private void updateDataBase() throws SQLException, FileNotFoundException {
        Connection con = DriverManager.getConnection(url, user, password);
        ScriptRunner sr = new ScriptRunner(con);


        Reader reader = new BufferedReader(new FileReader("src/main/resources/clear_tables.sql"));

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
        con.close();
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
        for(int index = 0; index < searchList.size(); index++){
            if(fullName.equals(searchList.get(index).getFullName())){
                searchList.remove(searchList.get(index));
                searchList.add(person);
                break;
            }
        }

        try {
            updateDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

        try {
            updateDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPerson(String[] personData, Boolean isMentor) {

        if(isMentor){
            mentors.add(new Mentor(personData));
        }
        else{
            Candidate newCandidate = new Candidate(personData);
            for(Person person: candidates){
                Candidate singleCandidate = (Candidate) person;
                if(newCandidate.getApplicationCode().equals((singleCandidate.getApplicationCode()))){
                   return;
                }
            }
            candidates.add(newCandidate);
        }

        try {
            updateDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    @Override
    public Mentor getMentorByFullName(String fullName){
            for(Person person : mentors) {
                if (person.getFullName().equals(fullName)) {
                    return (Mentor) person;
                }
            }
            return null;
    }

}
