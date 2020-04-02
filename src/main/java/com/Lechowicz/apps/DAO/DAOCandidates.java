package com.Lechowicz.apps.DAO;

import com.Lechowicz.apps.persons.Candidate;
import com.Lechowicz.apps.persons.Mentor;
import com.Lechowicz.apps.persons.Person;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DAOCandidates extends DAOsql implements InterfaceDAO {
    public DAOCandidates() throws IOException, SQLException {
        super();
    }


    @Override
    public List<Person> getPersons() {
        return super.candidates;
    }

    @Override
    public void updatePerson(String fullName, Person person) {

        for(int index = 0; index < candidates.size(); index++){
            if(fullName.equals(candidates.get(index).getFullName())){
                candidates.remove(candidates.get(index));
                candidates.add(person);
                break;
            }
        }

        try {
            super.updateDataBase();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
