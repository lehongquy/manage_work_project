package com.example.managerworkofstatecadres.listVehicle;

import java.util.Date;

public class licenad {
    String nametx,license, departure,trip,people;

    public licenad() {
    }

    public licenad(String nametx, String license, String trip, String departure, String people) {
        this.nametx = nametx;
        this.license = license;
        this.trip = trip;
        this.departure = departure;
        this.people = people;
    }

    public String getNametx() {
        return nametx;
    }


    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setNametx(String nametx) {
        this.nametx = nametx;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}

