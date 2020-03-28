package com.Lechowicz.apps.application;

import com.Lechowicz.apps.interactions.*;
import com.Lechowicz.apps.logic.Model;
import com.Lechowicz.apps.persons.Person;

import java.io.IOException;
import java.util.List;

public class Controller {
    private Integer NUMBER_OF_QUESTIONS = 7;
    private View view;
    private InputManager input;
    private Model model;

    private String[] questions;

    public Controller() throws IOException {
        input = new TerminalInput();
        view = new TerminalView();
        model = new Model();
        questions = new String[NUMBER_OF_QUESTIONS];
        fillQuestions();
        runProgram();
    }

    private void runProgram(){
        Boolean isRun = true;
        while(isRun){
            isRun = mainMenu();
        }
    }

    private Boolean mainMenu(){
        printQuestions();
        Integer numberOfAnswer = input.getIntFromUser();
        return answerSwitch(numberOfAnswer) > 0;
    }

    private void fillQuestions() {
        questions[0] = "Write a query that returns the 2 name columns of the mentors table\n";
        questions[1] = "Write a query that returns the nick_name-s of all mentors working at Miskolc.\n";
        questions[2] = "Write a query that returns the full name and phone number of all applications.\n";
        questions[3] = "Write a query that returns the full name, phone number and with special e-mail of all applications.\n";
        questions[4] = "Write a query that insert new application and return this person.\n";
        questions[5] = "Write a query that update application.\n";
        questions[6] = "Write a query that delete applications from database.\n";
    }

    private void printQuestions(){
        view.print(String.format("%d. %s", 0, "Please insert 0 to exit\n"));
        Integer index = 1;
        for(String question : questions){
            view.print(String.format("%d. %s", index++, question));
        }
    }


    private void answerOne(){
        view.print(questions[0]);
        List<Person> mentors = model.getAllMentors();
        view.print(mentors);
    }

    private void answerTwo(){
        view.print("Write a query that returns the nick_name-s of all mentors working at Miskolc.\n");
    }

    private void answerThree(){
        view.print("Write a query that returns the full name and phone number of all applications.\n");
    }

    private void answerFour(){
        view.print("Write a query that returns the full name, phone number and with special e-mail of all applications.\n");
    }

    private void answerFive(){
        view.print("Write a query that insert new application and return this person.\n");
    }

    private void answerSix(){
        view.print("Write a query that update application.\n");
    }

    private void answerSeven(){
        view.print("Write a query that delete applications from database.");
    }


    private Integer answerSwitch(Integer numberOfAnswer){
        switch(numberOfAnswer) {
            case 0:
                break;
            case 1:
                answerOne();
                break;
            case 2:
                answerTwo();
                break;
            case 3:
                answerThree();
                break;
            case 4:
                answerFour();
                break;
            case 5:
                answerFive();
                break;
            case 6:
                answerSix();
                break;
            case 7:
                answerSeven();
                break;
            default:
                view.print("Wrong input! Please insert the number from 0 to 7.\n");
            }

        return numberOfAnswer;
        }

}