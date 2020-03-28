package com.Lechowicz.apps.DAO;

import com.Lechowicz.apps.persons.Mentor;
import com.Lechowicz.apps.persons.Person;

import java.util.List;

public class DAOsql implements InterfaceDAO {
    private List<Person> mentors;
    private List<Person> candidates;

    DAOsql(){

    }

    @Override
    public List<Person> getMentors() {
        return mentors;
    }

    @Override
    public List<Person> getCandidates() {
        return candidates;
    }

    @Override
    public void updatePerson(String fullName, Person person) {
        List<Person> searchList;
        if(person instanceof Mentor){
          searchList = mentors;
        }
        else{
            searchList = candidates;
        }
        for(Person singlePerson: searchList){
            if(fullName.equals(singlePerson.getFullName())){
                searchList.remove(singlePerson);
                searchList.add(person);
            }
        }
    }

    @Override
    public void deletePerson(String fullName) {

    }

    @Override
    public void addPerson(String fullName) {

    }


    private void readFromDB(){

    }
}
