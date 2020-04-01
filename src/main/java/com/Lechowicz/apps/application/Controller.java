package com.Lechowicz.apps.application;

import com.Lechowicz.apps.interactions.*;
import com.Lechowicz.apps.logic.Model;


import java.io.IOException;
import java.sql.SQLException;


public class Controller {
    private View view;
    private InputManager input;
    private ControllerBasic controllerBasic;
    private ControllerAdvance controllerAdvance;


    public Controller() throws IOException, SQLException {
        input = new TerminalInput();
        view = new TerminalView();
        Model model = new Model();
        controllerBasic = new ControllerBasic(model, view, input);
        controllerAdvance = new ControllerAdvance(model, view, input);
    }

    public void runProgram(){
        Boolean isRun = true;
        while(isRun){
            isRun = mainMenu();
        }
    }

    private Boolean mainMenu(){
        displayOptions();
        Integer switchMenu = chooseOptions();
        switch(switchMenu){
            case 0:
                return false;
            case 1:
                return controllerBasic.standardMenu();
            case 2:
                return controllerAdvance.advanceMenu();
            case 3 :
                break;
            default:
                view.print("Choose wrong input!");
        }
        return true;
    }

    private void displayOptions(){
        view.print("Please choose submenu:\n0. Exit\n1. Standard questions.\n2. Add or update table\n" +
                   "3. Search in tables\n");
    }


    private Integer chooseOptions(){


        Integer numberOfAnswer = input.getIntFromUser();

        return numberOfAnswer;
    }

}