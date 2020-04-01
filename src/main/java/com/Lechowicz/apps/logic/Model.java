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

    public void addNewAplication(String[] personData){
        daoDB.addPerson(personData, false);
    }

    public void addNewMentor(String[] personData){
        daoDB.addPerson(personData, true);
    }

    public Candidate getCandidate(Integer appCode){
        return daoDB.getCandidateByCode(appCode);
    }

    public void updatePerson(String fullName, Person updatePerson){
        daoDB.updatePerson(fullName, updatePerson);
    }

    public void deleteAplication(List<Person> candidatesToRemove){
        for(Person candidate: candidatesToRemove){
            daoDB.deletePerson(candidate);
        }
    }

    public Candidate getCandidate(String fullName) {
        return daoDB.getCandidateByFullName(fullName);
    }

    public Mentor getMentor(String fullName) {
        return daoDB.getMentorByFullName(fullName);
    }

    public Mentor getMentorByNick(String nickName){
        List<Person> mentors = daoDB.getMentors();
        for(Person person : mentors){
            if(((Mentor) person).getNickName().equals(nickName)){
                return (Mentor) person;
            }
        }
        return null;
    }

    public Mentor getMentorByPhone(String phone){
        List<Person> mentors = daoDB.getMentors();
        for(Person person : mentors){
            if(person.getPhoneNumber().equals(phone)){
                return (Mentor) person;
            }
        }
        return null;
    }

    public Candidate getCandidateByPhone(String phone){
        List<Person> candidates = daoDB.getCandidates();
        for(Person person : candidates){
            if(person.getPhoneNumber().equals(phone)){
                return (Candidate) person;
            }
        }
        return null;
    }

    public Candidate getCandidateByEmail(String mail) {
        List<Person> candidates = daoDB.getCandidates();
        for(Person person : candidates){
            if(person.getEmail().equals(mail)){
                return (Candidate) person;
            }
        }
        return null;
    }

    public Mentor getMentorByEmail(String mail) {
        List<Person> mentors = daoDB.getMentors();
        for(Person person : mentors){
            if(person.getEmail().equals(mail)){
                return (Mentor) person;
            }
        }
        return null;
    }

    public Mentor getMentorByFavNmb(Integer nmb) {
        List<Person> mentors = daoDB.getMentors();
        for(Person person : mentors){
            Mentor mentor = (Mentor) person;
            if(mentor.getFavouriteNumber() == null){
                continue;
            }
            if(mentor.getFavouriteNumber().equals(nmb)){
                return mentor;
            }
        }
        return null;
    }
}
