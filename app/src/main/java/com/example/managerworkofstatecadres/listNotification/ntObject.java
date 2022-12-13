package com.example.managerworkofstatecadres.listNotification;

public class ntObject {
    String nament, contextnt, timent, critical;

    public ntObject() {
    }

    public ntObject(String nament, String contextnt, String timent, String critical) {
        this.nament = nament;
        this.contextnt = contextnt;
        this.timent = timent;
        this.critical = critical;
    }

    public String getNament() {
        return nament;
    }

    public void setNament(String nament) {
        this.nament = nament;
    }

    public String getContextnt() {
        return contextnt;
    }

    public void setContextnt(String contextnt) {
        this.contextnt = contextnt;
    }

    public String getTiment() {
        return timent;
    }

    public void setTiment(String timent) {
        this.timent = timent;
    }


    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }
}
