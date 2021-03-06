package com.Lechowicz.apps.interactions;

import com.Lechowicz.apps.persons.Candidate;
import com.Lechowicz.apps.persons.Mentor;
import com.Lechowicz.apps.persons.Person;

import java.util.List;

public class TerminalView extends View {
    @Override
    public void print(String toDisplay){
        System.out.print(toDisplay);
    }

    @Override
    public void print(Candidate candidate) {
        System.out.println(candidate);
    }

    @Override
    public void print(Mentor mentor) {
        System.out.println(mentor);
    }

    @Override
    public void print(List<Mentor> mentors) {
        for(Mentor singleMentor: mentors)
            System.out.println(singleMentor);
    }

    @Override
    public void printFullName(List<Person> listOfPerson) {
        clearScreen();
        System.out.println("first_name   |   last_name");
        System.out.println("-------------+------------");
        for(Person person: listOfPerson){
            System.out.println(String.format("%-11s  |  %s", person.getFirstName(), person.getLastName()));
        }
    }

    @Override
    public void printNickName(List<Mentor> mentors) {
        clearScreen();
        System.out.println("nick_name");
        System.out.println("---------");
        for(Mentor mentor: mentors){
            System.out.println(mentor.getNickName());
        }
    }

    @Override
    public void printFullNameAndPhone(List<Person> persons) {
        clearScreen();
        System.out.println("full_name                | phone_number");
        System.out.println("-------------------------+---------------------------");
        for(Person person: persons){
            System.out.println(String.format("%-23s  |  %s", person.getFullName(), person.getPhoneNumber()));
        }
    }

    @Override
    public void printFullName(Candidate candidate) {
        clearScreen();
        System.out.println("first_name   |   last_name");
        System.out.println("-------------+------------");
        System.out.println(String.format("%-11s  |  %s", candidate.getFirstName(), candidate.getLastName()));

    }

    private void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
