package com.Lechowicz.apps.application;

import com.Lechowicz.apps.interactions.*;
import com.Lechowicz.apps.logic.Model;
import com.Lechowicz.apps.persons.Candidate;
import com.Lechowicz.apps.persons.Mentor;
import com.Lechowicz.apps.persons.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    private Integer NUMBER_OF_QUESTIONS = 7;
    private Integer NUMBER_OF_OPTIONS = 4;
    private View view;
    private InputManager input;
    private Model model;

    private String[] questions;
    private String[] options;

    public Controller() throws IOException, SQLException {
        input = new TerminalInput();
        view = new TerminalView();
        model = new Model();
        questions = new String[NUMBER_OF_QUESTIONS];
        options = new String[NUMBER_OF_OPTIONS];
        fillQuestions();
        fillOptions();
        runProgram();
    }

    private void runProgram(){
        Boolean isRun = true;
        while(isRun){
            isRun = mainMenu();
        }
    }

    private Boolean mainMenu(){
        Integer switchMenu = chooseOptions();
        if(switchMenu == 1){
            return standardMenu();
        }
        else{
            return advanceMenu();
        }
    }

    private Boolean advanceMenu(){
        printOptions();
        Integer numberOfOption = input.getIntFromUser();
        return optionSwitch(numberOfOption) > 0;
    }

    private Integer optionSwitch(Integer numberOfOption) {
        switch (numberOfOption){
            case 1:
                createApplication();
                break;
            case 2:
                createMentor();
                break;
            case 3:
                updateApplication();
                break;
            case 4:
                updateMentor();
                break;
            case 5:
                searchTables();
                break;
            default:
                view.print("Wrong input! Please insert the number from 0 to 4.\n");
        }
        return numberOfOption;
    }

    private void searchTables() {
    }

    private void updateMentor() {
    }

    private void updateApplication() {
    }

    private void createMentor() {
    }

    private void createApplication() {
        String[] personData = new String[6];
        view.print("Please insert id: ");
        personData[0] = String.format("%s", input.getIntFromUser());
        view.print("Please insert first name: ");
        personData[1] = input.getStringFromUser();
        view.print("Please insert last name: ");
        personData[2] = input.getStringFromUser();
        view.print("Please insert phone number: ");
        personData[3] = input.getStringFromUser();
        view.print("Please insert email: ");
        personData[4] = input.getStringFromUser();
        view.print("Please insert application code: ");
        personData[5] = String.format("%s", input.getIntFromUser());

        model.addNewAplication(personData);
    }

    private void fillOptions() {
        options[0] = "Add new application to database.\n";
        options[1] = "Add new mentor to database.\n";
        options[2] = "Update application.\n";
        options[3] = "Update mentor.\n";
        options[4] = "Search phrase in database\n";
    }

    private void printOptions() {
        view.print(String.format("%d. %s", 0, "Please insert 0 to exit\n"));
        Integer index = 1;
        for(String option : options){
            view.print(String.format("%d. %s", index++, option));
        }
    }

    private Boolean standardMenu(){
        printQuestions();
        Integer numberOfAnswer = input.getIntFromUser();
        return answerSwitch(numberOfAnswer) > 0;
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
        view.printFullName(mentors);
    }

    private void answerTwo(){
        view.print(questions[1]);
        List<Mentor> mentors =  model.getMentorsByCity("Miskolc");
        view.printNickName(mentors);
    }

    private void answerThree(){
        view.print(questions[2]);
        List<Person> candidates = model.getAllAplications();
        view.printFullNameAndPhone(candidates);
    }

    private void answerFour(){
        view.print(questions[3]);
        List<Person> candidates = model.getCandidateByEmailPattern("@adipiscingenimmi.edu");
        view.printFullNameAndPhone(candidates);
    }

    private void answerFive(){
        view.print(questions[4]);
        String[] newPersonData = {"11" , "Markus", "Schaffarzyk", "003620/725-2666", "djnovus@groovecoverage.com", "54823"};
        model.addNewAplication(newPersonData);
        Candidate newCandidate = model.getCandidate(54823);
        view.printFullName(newCandidate);
    }

    private void answerSix(){
        String fullName = "Jemima Foreman";
        view.print(questions[5]);
        Candidate application = model.getCandidate(fullName);
        application.setPhoneNumber("003670/223-7459");
        model.updateAplication(fullName, application);
        view.printFullName(model.getCandidate(fullName));
    }

    private void answerSeven(){
        view.print(questions[6]);
        List<Person> candidates = model.getCandidateByEmailPattern("@mauriseu.net");
        model.deleteAplication(candidates);
        view.printFullName(model.getAllAplications());
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