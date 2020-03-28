package com.Lechowicz.apps.application;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try {
            new Controller();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
