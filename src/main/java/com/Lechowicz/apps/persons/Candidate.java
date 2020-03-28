package com.Lechowicz.apps.persons;

public class Candidate extends Person {
    private Integer applicationCode;

    public Candidate(String[] infoAboutPerson) {
        super(infoAboutPerson);
        this.phoneNumber = infoAboutPerson[3];
        this.email = infoAboutPerson[4];
        this.applicationCode = Integer.parseInt(infoAboutPerson[5]);
    }

    public Integer getApplicationCode() {
        return applicationCode;
    }
}
