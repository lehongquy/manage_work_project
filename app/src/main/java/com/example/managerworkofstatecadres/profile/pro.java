package com.example.managerworkofstatecadres.profile;

public class pro {
    String fullname, gmail, phone, position;
    byte[] image;

    public pro() {
    }

    public pro(String fullname, String gmail, String phone, String position, byte[] image) {
        this.fullname = fullname;
        this.gmail = gmail;
        this.phone = phone;
        this.position = position;
        this.image = image;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}