package com.Lechowicz.apps.persons;

public abstract class Person {
    protected Integer id;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String email;

    public Person(String[] infoAboutPerson) {
        this.id = Integer.parseInt(infoAboutPerson[0]);
        this.firstName = infoAboutPerson[1];
        this.lastName = infoAboutPerson[2];
        this.phoneNumber = infoAboutPerson[3];
        this.email = infoAboutPerson[4];
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
