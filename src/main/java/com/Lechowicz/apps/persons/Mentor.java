package com.Lechowicz.apps.persons;

public class Mentor extends Person {
    private String city;
    private Integer favouriteNumber;

    public Mentor(String[] param) {
        super(param);
        city = param[5];
        favouriteNumber = Integer.parseInt(param[6]);
    }

    public String getCity() {
        return city;
    }

    public Integer getFavouriteNumber() {
        return favouriteNumber;
    }


}
