package com.Lechowicz.apps.DAO;

import com.Lechowicz.apps.persons.Candidate;
import com.Lechowicz.apps.persons.Mentor;
import com.Lechowicz.apps.persons.Person;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DAOMentors extends DAOsql implements InterfaceDAO {
    public DAOMentors() throws IOException, SQLException {
        super();
    }

    @Override
    public List<Person> getPersons() {
        return super.mentors;
    }

    @Override
    public void updatePerson(String fullName, Person person) {

        for(int index = 0; index < mentors.size(); index++){
            if(fullName.equals(mentors.get(index).getFullName())){
                mentors.remove(mentors.get(index));
                mentors.add(person);
                break;
            }
        }

        try {
            super.updateDataBase();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deletePerson(Person person) {
        mentors.removeIf(singlePerson -> person == singlePerson);

        try {
            updateDataBase();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPerson(String[] personData, Boolean isMentor) {
        mentors.add(new Mentor(personData));

        try {
            updateDataBase();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
