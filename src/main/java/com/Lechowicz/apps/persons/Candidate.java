package com.Lechowicz.apps.persons;

public class Candidate extends Person {
    protected Integer applicationCode;

    public Candidate(String[] infoAboutPerson) {
        super(infoAboutPerson);

        this.applicationCode = Integer.parseInt(infoAboutPerson[6]);
    }

    public Integer getApplicationCode() {
        return applicationCode;
    }
}
