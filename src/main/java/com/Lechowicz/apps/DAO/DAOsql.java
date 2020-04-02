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


public abstract class DAOsql implements InterfaceDAO {
    protected List<Person> mentors;
    protected List<Person> candidates;

    protected String url;
    protected String user;
    protected String password;
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
        fillLists();
    }

    public static Properties readProperties(String path) throws IOException {

        Properties props = new Properties();
        Path myPath = Paths.get(path);

        BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);
        props.load(bf);

        return props;
    }

    private void fillLists() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, password);
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

}
