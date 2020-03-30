package com.Lechowicz.apps.interactions;

import com.Lechowicz.apps.persons.Candidate;
import com.Lechowicz.apps.persons.Mentor;
import com.Lechowicz.apps.persons.Person;

import java.util.List;

public abstract class View {
    public abstract void print(String toDisplay);

    public abstract void printFullName(List<Person> mentors);

    public abstract void printNickName(List<Mentor> mentors);

    public abstract void printFullNameAndPhone(List<Person> persons);

    public abstract void printFullName(Candidate candidate);
}
