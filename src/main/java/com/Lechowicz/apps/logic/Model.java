package com.Lechowicz.apps.logic;

import com.Lechowicz.apps.DAO.DAOsql;
import com.Lechowicz.apps.DAO.InterfaceDAO;
import com.Lechowicz.apps.persons.Candidate;
import com.Lechowicz.apps.persons.Mentor;
import com.Lechowicz.apps.persons.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model {
    InterfaceDAO daoDB;

    public Model() throws IOException, SQLException {
        daoDB = new DAOsql();
    }

    public List<Person> getAllMentors() {
        return daoDB.getMentors();
    }

    public List<Person>  getAllAplications(){
        return daoDB.getCandidates();
    }

    public List<Mentor> getMentorsByCity(String city){
        List<Mentor> mentors = new ArrayList<>();
        for(Person person: this.getAllMentors()){
            Mentor mentor = (Mentor) person;
            if(mentor.getCity().equals(city)){
                mentors.add(mentor);
            }
        }
        return mentors;
    }

    public List<Person> getCandidateByEmailPattern(String pattern){
        List<Person> candidates = new ArrayList<>();
        for(Person person: this.getAllAplications()){
            if(person.getEmail().contains(pattern)){
                candidates.add(person);
            }
        }
        return candidates;
    }

    public void addNewAplication(Person newPerson){

    }

    public void updateAplication(String fullName, Person updatePerson){

    }

    public void deleteAplication(String emailPattern){

    }
}
