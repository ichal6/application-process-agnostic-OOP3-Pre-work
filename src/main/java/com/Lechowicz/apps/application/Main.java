package com.Lechowicz.apps.application;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        try {
            Controller contr = new Controller();
            contr.runProgram();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed open database. Program doesn't run.");
            e.printStackTrace();
        }
    }
}
