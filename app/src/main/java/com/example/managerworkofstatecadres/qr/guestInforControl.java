package com.example.managerworkofstatecadres.qr;

public class guestInforControl {
    private String fullname;
    private String gmail;
    private String phone;
    private String position;

  public guestInforControl() {
  }

  public guestInforControl(String fullname, String gmail, String phone, String position) {
        this.fullname = fullname;
        this.gmail = gmail;
        this.phone = phone;
        this.position = position;
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

    @Override
    public String toString() {
        return
                "fullname='" + fullname + '\'' +
                        ", gmail='" + gmail + '\'' +
                        ", phone='" + phone + '\'' +
                        ", position='" + position + '\'';
    }
}
