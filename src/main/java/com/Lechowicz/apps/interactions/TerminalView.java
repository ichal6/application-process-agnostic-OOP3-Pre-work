package com.Lechowicz.apps.interactions;

import com.Lechowicz.apps.persons.Person;

import java.util.List;

public class TerminalView extends View {
    @Override
    public void print(String toDisplay){
        System.out.print(toDisplay);
    }

    @Override
    public void print(List<Person> listOfPerson) {
        for(Person person: listOfPerson){
            System.out.println(person.getFullName());
        }
    }
}
