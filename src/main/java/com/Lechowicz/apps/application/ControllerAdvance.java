package com.Lechowicz.apps.application;

import com.Lechowicz.apps.interactions.InputManager;
import com.Lechowicz.apps.interactions.View;
import com.Lechowicz.apps.logic.Model;
import com.Lechowicz.apps.persons.*;

import java.util.List;

public class ControllerAdvance {
    View view;
    InputManager input;
    private Integer NUMBER_OF_OPTIONS = 4;
    private Model model;
    private String[] options;


    public ControllerAdvance(Model model, View view, InputManager input){
        this.view = view;
        this.input = input;
        options = new String[NUMBER_OF_OPTIONS];
        this.model = model;

        fillOptions();
    }

    public Boolean advanceMenu(){
        printOptions();
        Integer numberOfOption = input.getIntFromUser();
        return optionSwitch(numberOfOption) > 0;
    }

    private Integer optionSwitch(Integer numberOfOption) {
        switch (numberOfOption){
            case 0:
                break;
            case 1:
                createApplication();
                break;
            case 2:
                createMentor();
                break;
            case 3:
                updatePerson(false);
                break;
            case 4:
                updatePerson(true);
                break;
            default:
                view.print("Wrong input! Please insert the number from 0 to 4.\n");
        }
        return numberOfOption;
    }

    private Person searchPerson(Boolean isMentor){
        view.print("Please insert full name of search person: ");
        String fullName = input.getStringFromUser();
        if(isMentor){
            return model.getMentor(fullName);
        }else{
            return model.getCandidate(fullName);
        }
    }

    private void updatePerson(Boolean isMentor) {
        Person personToUpdate = searchPerson(isMentor);

        if( personToUpdate == null){
            view.print("Wrong input back to main menu\n");
            return;
        }
        view.print("Please choose options edit:\n");
        view.print("1. edit phone number\n");
        view.print("2. edit email\n");
        if(input.getIntFromUser().equals(1)){
            view.print("Please insert new phone number: ");
            String phone = input.getStringFromUser();
            personToUpdate.setPhoneNumber(phone);
        }
        else{
            view.print("Please insert email: ");
            String email = input.getStringFromUser();
            personToUpdate.setEmail(email);
        }
        model.updatePerson(personToUpdate.getFullName(), personToUpdate);
    }

    private void createMentor() {
        String[] personData = new String[8];
        view.print("Please insert id: ");
        personData[0] = String.format("%s", input.getIntFromUser());
        view.print("Please insert first name: ");
        personData[1] = input.getStringFromUser();
        view.print("Please insert last name: ");
        personData[2] = input.getStringFromUser();
        view.print("Please insert nick_name: ");
        personData[3] = input.getStringFromUser();
        view.print("Please insert phone number: ");
        personData[4] = input.getStringFromUser();
        view.print("Please insert email: ");
        personData[5] = input.getStringFromUser();
        view.print("Please insert city: ");
        personData[6] = input.getStringFromUser();
        view.print("Does (s)he the favourite number?[Y/N]");
        if(input.getStringFromUser().toUpperCase().equals("Y")){
            view.print("Please insert favourite_number: ");
            personData[7] = String.format("%s", input.getIntFromUser());
        }
        model.addNewMentor(personData);
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
    }

    private void printOptions() {
        view.print(String.format("%d. %s", 0, "Back to main menu\n"));
        Integer index = 1;
        for(String option : options){
            view.print(String.format("%d. %s", index++, option));
        }
    }
}
