package com.Lechowicz.apps.application;

import com.Lechowicz.apps.DAO.DAOCandidates;
import com.Lechowicz.apps.DAO.DAOMentors;
import com.Lechowicz.apps.DAO.InterfaceDAO;
import com.Lechowicz.apps.persons.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model {
    InterfaceDAO daoMentors;
    InterfaceDAO daoCandidates;
    List<Person> listMentors;
    List<Person> listCandidates;

    public Model() throws IOException, SQLException {
        daoMentors = new DAOMentors();
        daoCandidates = new DAOCandidates();
        listMentors = getAllMentors();
        listCandidates = getAllApplications();
    }

    public List<Person> getAllMentors() {
        return daoMentors.getPersons();
    }

    public List<Person> getAllApplications(){
        return daoCandidates.getPersons();
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
        for(Person person: this.getAllApplications()){
            if(person.getEmail().contains(pattern)){
                candidates.add(person);
            }
        }
        return candidates;
    }

    public void addNewApplication(String[] personData){
        daoCandidates.addPerson(personData, false);
    }

    public void addNewMentor(String[] personData){
        daoMentors.addPerson(personData, true);
    }

    public Candidate getCandidate(Integer appCode){
        for(Person person : listCandidates){
            Candidate candidate = (Candidate) person;
            if(candidate.getApplicationCode().equals(appCode)){
                return candidate;
            }
        }

        return null;
    }

    public void updateMentor(String fullName, Person updatePerson){
        daoMentors.updatePerson(fullName, updatePerson);
    }

    public void updateCandidate(String fullName, Person updatePerson){
        daoCandidates.updatePerson(fullName, updatePerson);
    }

    public void deleteApplication(List<Person> candidatesToRemove){
        for(Person candidate: candidatesToRemove){
            daoCandidates.deletePerson(candidate);
        }
    }

    public Candidate getCandidate(String fullName) {
        for(Person person : listCandidates){
            if(person.getFullName().equals(fullName)){
                return (Candidate) person;
            }
        }
        return null;
    }

    public Mentor getMentor(String fullName) {
        for(Person person : listMentors) {
            if (person.getFullName().equals(fullName)) {
                return (Mentor) person;
            }
        }
        return null;
    }

    public Mentor getMentorByNick(String nickName){
        List<Person> mentors = listMentors;
        for(Person person : mentors){
            if(((Mentor) person).getNickName().equals(nickName)){
                return (Mentor) person;
            }
        }
        return null;
    }

    public Mentor getMentorByPhone(String phone){
        List<Person> mentors = listMentors;
        for(Person person : mentors){
            if(person.getPhoneNumber().equals(phone)){
                return (Mentor) person;
            }
        }
        return null;
    }

    public Candidate getCandidateByPhone(String phone){
        List<Person> candidates = listCandidates;
        for(Person person : candidates){
            if(person.getPhoneNumber().equals(phone)){
                return (Candidate) person;
            }
        }
        return null;
    }

    public Candidate getCandidateByEmail(String mail) {
        List<Person> candidates = listCandidates;
        for(Person person : candidates){
            if(person.getEmail().equals(mail)){
                return (Candidate) person;
            }
        }
        return null;
    }

    public Mentor getMentorByEmail(String mail) {
        List<Person> mentors = listMentors;
        for(Person person : mentors){
            if(person.getEmail().equals(mail)){
                return (Mentor) person;
            }
        }
        return null;
    }


    public List<Mentor> getMentorByFavNmb(Integer nmb) {
        List<Person> mentors = daoMentors.getPersons();
        List<Mentor> searchMentors = new ArrayList<>();
        for(Person person : mentors){
            Mentor mentor = (Mentor) person;
            if(mentor.getFavouriteNumber() == null){
                continue;
            }
            if(mentor.getFavouriteNumber().equals(nmb)){
                searchMentors.add(mentor);
            }
        }
        return searchMentors;
    }
}
