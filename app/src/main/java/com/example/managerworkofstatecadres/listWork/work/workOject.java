package com.example.managerworkofstatecadres.listWork.work;

public class workOject {
    String namecv,contextcv,timecv,location,floor,room;

    public workOject() {
    }

    public workOject(String namecv,String contextcv,String timecv, String location, String floor, String room) {
        this.namecv = namecv;
        this.contextcv = contextcv;
        this.timecv = timecv;
        this.location = location;
        this.floor = floor;
        this.room = room;
    }

    public String getNamecv() {
        return namecv;
    }

    public void setNamecv(String namecv) {
        this.namecv = namecv;
    }

    public String getContextcv() {
        return contextcv;
    }

    public void setContextcv(String contextcv) {
        this.contextcv = contextcv;
    }

    public String getTimecv() {
        return timecv;
    }

    public void setTimecv(String timecv) {
        this.timecv = timecv;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
