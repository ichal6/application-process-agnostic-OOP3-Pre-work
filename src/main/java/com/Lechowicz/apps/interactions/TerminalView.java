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
    public void printFullName(List<Person> listOfPerson) {
        System.out.println("first_name   |   last_name");
        System.out.println("-------------+------------");
        for(Person person: listOfPerson){
            System.out.println(String.format("%-11s  |  %s", person.getFirstName(), person.getLastName()));
        }
    }

    @Override
    public void printNickName(List<Mentor> mentors) {
        System.out.println("nick_name");
        System.out.println("---------");
        for(Mentor mentor: mentors){
            System.out.println(mentor.getNickName());
        }
    }

    @Override
    public void printFullNameAndPhone(List<Person> persons) {
        System.out.println("full_name                | phone_number");
        System.out.println("-------------------------+---------------------------");
        for(Person person: persons){
            System.out.println(String.format("%-23s  |  %s", person.getFullName(), person.getPhoneNumber()));
        }
    }

    @Override
    public void print(Candidate newCandidate) {
        System.out.println(newCandidate.getFullName() + " " + newCandidate.getPhoneNumber());
    }

}
