package com.Lechowicz.apps.persons;

public class Mentor extends Person {
    private String city;
    private Integer favouriteNumber;
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public Mentor(String[] infoAboutPerson) {
        super(infoAboutPerson);
        this.nickName = infoAboutPerson[3];
        this.phoneNumber = infoAboutPerson[4];
        this.email = infoAboutPerson[5];
        this.city = infoAboutPerson[6];
        if(infoAboutPerson[7] != null){
            this.favouriteNumber = Integer.parseInt(infoAboutPerson[7]);
        }

    }

    public String getCity() {
        return city;
    }

    public Integer getFavouriteNumber() {
        return favouriteNumber;
    }

    @Override
    public String toString(){
        String returnValue;
        returnValue = String.format("%d, %s, %s, %s, %s, %s, %s, %d",
                this.getId(), this.getFirstName(), this.getLastName(), this.getNickName(), this.getPhoneNumber(),
                this.getEmail(), this.getCity(), this.getFavouriteNumber());
        return returnValue;
    }

}
