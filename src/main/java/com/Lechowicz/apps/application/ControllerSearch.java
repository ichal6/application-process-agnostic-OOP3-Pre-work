package com.Lechowicz.apps.application;

import com.Lechowicz.apps.interactions.InputManager;
import com.Lechowicz.apps.interactions.View;
import com.Lechowicz.apps.persons.Candidate;
import com.Lechowicz.apps.persons.Mentor;
import com.Lechowicz.apps.persons.Person;

import java.util.List;

public class ControllerSearch {
    private View view;
    private InputManager input;
    private Model model;

    public ControllerSearch(Model model, View view, InputManager input){
        this.view = view;
        this.input = input;
        this.model = model;
    }

    private void searchByFullName(){
        view.print("Please insert full name\n");
        String fullName = input.getStringFromUser();
        Person foundPerson = model.getMentor(fullName);
        if(foundPerson != null){
            view.print((Mentor)foundPerson);
        }
        else{
            foundPerson = model.getCandidate(fullName);
            view.print((Candidate)foundPerson);
        }
    }

    private void searchByNickName(){
        view.print("Please insert nick name\n");
        String nick = input.getStringFromUser();
        Mentor foundPerson = model.getMentorByNick(nick);
        if(foundPerson != null) {
            view.print(foundPerson);
        }
    }

    private void searchByPhoneNumber() {
        view.print("Please insert phone number\n");
        String phone = input.getStringFromUser();
        Person foundPerson = model.getMentorByPhone(phone);
        if(foundPerson != null){
            view.print((Mentor)foundPerson);
        }
        else{
            foundPerson = model.getCandidateByPhone(phone);
            view.print((Candidate)foundPerson);
        }
    }

    private void searchByMail() {
        view.print("Please insert e-mail\n");
        String mail = input.getStringFromUser();
        Person foundPerson = model.getMentorByEmail(mail);
        if(foundPerson != null){
            view.print((Mentor)foundPerson);
        }
        else{
            foundPerson = model.getCandidateByEmail(mail);
            view.print((Candidate)foundPerson);
        }
    }

    private void searchByFavNum() {
        view.print("Please insert favourite number: \n");
        Integer number = input.getIntFromUser();
        view.print(model.getMentorByFavNmb(number));
    }

    private void searchByAppCode() {
        view.print("Please insert appCode: \n");
        String code = input.getStringFromUser();
        Candidate foundCandidate = model.getCandidate(code);
        if(foundCandidate != null) {
            view.print(foundCandidate);
        }
    }

    private void searchByCity() {
        view.print("Please insert city\n");
        String city = input.getStringFromUser();
        List<Mentor> foundPersons = model.getMentorsByCity(city);
        if(foundPersons != null) {
            view.print(foundPersons);
        }
    }

    public void searchTables() {
        view.print("Please choose option to search:\n");
        view.print("0. Exit\n1. full_name\n2. nick_name\n3. phone_number\n4. e-mail\n5. city\n" +
                "6. application_code\n7. favourite_number\n");
        Integer numberOfOption = input.getIntFromUser();
        switch(numberOfOption){
            case 0:
                break;
            case 1:
                searchByFullName();
                break;
            case 2:
                searchByNickName();
                break;
            case 3:
                searchByPhoneNumber();
                break;
            case 4:
                searchByMail();
                break;
            case 5:
                searchByCity();
                break;
            case 6:
                searchByAppCode();
                break;
            case 7:
                searchByFavNum();
                break;
            default:
                view.print("Insert wrong input!");
        }
    }

}
