package com.example.managerworkofstatecadres.qr;

public class inforGuest {
    String fullname, gmail,position,phone;

    public inforGuest(String fullname, String gmail, String position, String phone) {
        this.fullname = fullname;
        this.gmail = gmail;
        this.position = position;
        this.phone = phone;
    }

    public inforGuest() {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return
                "Name='" + fullname + '\n' +
                "gmail='" + gmail + '\n' +
                "position='" + position + '\n' +
                " phone='" + phone
              ;
    }
}
