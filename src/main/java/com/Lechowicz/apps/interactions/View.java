package com.Lechowicz.apps.interactions;

import com.Lechowicz.apps.persons.Person;

import java.util.List;

public abstract class View {
    public abstract void print(String toDisplay);

    public abstract void print(List<Person> mentors);
}
