package com.Lechowicz.apps.DAO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;


public abstract class DAOsql implements InterfaceDAO {
    protected String url;
    protected String user;
    protected String password;

    public DAOsql() throws IOException, SQLException{
        Properties prop = readProperties("src/main/resources/database.properties");
        url = prop.getProperty("db.url");
        user = prop.getProperty("db.user");
        password = prop.getProperty("db.passwd");
    }

    public static Properties readProperties(String path) throws IOException {

        Properties props = new Properties();
        Path myPath = Paths.get(path);

        BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);
        props.load(bf);

        return props;
    }

}
