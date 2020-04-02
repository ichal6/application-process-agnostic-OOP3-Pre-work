package com.Lechowicz.apps.DAO;

import com.Lechowicz.apps.persons.Candidate;
import com.Lechowicz.apps.persons.Mentor;
import com.Lechowicz.apps.persons.Person;

import java.util.List;

public interface InterfaceDAO {

    public List<Person> getPersons();

    public void updatePerson(String fullName, Person person);

    public void deletePerson(Person person);

    public void addPerson(String[] personData, Boolean isMentor);

}
