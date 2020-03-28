package com.Lechowicz.apps.DAO;

import com.Lechowicz.apps.persons.Person;

import java.util.List;

public interface InterfaceDAO {

    public List<Person> getMentors();

    public List<Person> getCandidates();

    public void updatePerson(String fullName, Person person);

    public void deletePerson(String fullName);

    public void addPerson(String fullName);
}
