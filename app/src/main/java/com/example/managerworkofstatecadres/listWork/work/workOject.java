package com.example.managerworkofstatecadres.listWork.work;

import java.util.HashMap;
import java.util.Map;

public class workOject {
    String namecv, contextcv, timecv, location, floor, room, critical;

    public workOject() {
    }

    public workOject(String namecv, String contextcv, String timecv, String location, String floor, String room, String critical) {
        this.namecv = namecv;
        this.contextcv = contextcv;
        this.timecv = timecv;
        this.location = location;
        this.floor = floor;
        this.room = room;
        this.critical = critical;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
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

    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("namecv", namecv);
        map.put("contextcv", contextcv);
        map.put("timecv", timecv);
        map.put("location", location);
        map.put("floor", floor);
        map.put("room", room);
        map.put("critical", critical);

        return map;
    }
}
