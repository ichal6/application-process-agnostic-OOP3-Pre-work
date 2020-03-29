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
        for(Person person: listOfPerson){
            System.out.println(person.getFullName());
        }
    }

    @Override
    public void printNickName(List<Mentor> mentors) {
        for(Mentor mentor: mentors){
            System.out.println(mentor.getNickName());
        }
    }

    @Override
    public void printFullNameAndPhone(List<Person> persons) {
        for(Person person: persons){
            System.out.println(person.getFullName() + " " + person.getPhoneNumber());
        }
    }

    @Override
    public void print(Candidate newCandidate) {
        System.out.println(newCandidate.getFullName());
    }

}
