package com.example.managerworkofstatecadres.listVehicle;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class licenad {
    String namedriver,license, departure,trip,people,critical;

    public licenad() {
    }

    public String getNamedriver() {
        return namedriver;
    }

    public void setNamedriver(String namedriver) {
        this.namedriver = namedriver;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
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

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }


    public Map<String, Object> toMap() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("namedriver",namedriver);
        map.put("license",license);
        map.put("trip",trip);
        map.put("departure",departure);
        map.put("critical",critical);
        map.put("people",people);
                return map;
    }
}

