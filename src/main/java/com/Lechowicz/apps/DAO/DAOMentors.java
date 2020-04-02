package com.Lechowicz.apps.DAO;

import com.Lechowicz.apps.persons.Candidate;
import com.Lechowicz.apps.persons.Mentor;
import com.Lechowicz.apps.persons.Person;

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

}
