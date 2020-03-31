package com.Lechowicz.apps.application;

import com.Lechowicz.apps.interactions.*;
import com.Lechowicz.apps.logic.Model;
import com.Lechowicz.apps.persons.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    private View view;
    private InputManager input;
    private Model model;
    private ControllerBasic controllerBasic;
    private ControllerAdvance controllerAdvance;


    public Controller() throws IOException, SQLException {
        input = new TerminalInput();
        view = new TerminalView();
        model = new Model();
        controllerBasic = new ControllerBasic(model);
        controllerAdvance = new ControllerAdvance(model);
    }

    public void runProgram(){
        Boolean isRun = true;
        while(isRun){
            isRun = mainMenu();
        }
    }

    private Boolean mainMenu(){
        Integer switchMenu = chooseOptions();
        if(switchMenu == 1){
            return controllerBasic.standardMenu();
        }
        else{
            return controllerAdvance.advanceMenu();
        }
    }


    private Integer chooseOptions(){
        view.print("Please choose submenu:\n");
        view.print("1. Standard questions.\n");
        view.print("2. User's questions\n");

        Integer numberOfAnswer;
        do {
            numberOfAnswer = input.getIntFromUser();
        }while(numberOfAnswer < 0 || numberOfAnswer > 3);

        return numberOfAnswer;
    }

}