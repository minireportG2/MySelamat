package com.example.myselamat;

public class SopModel {

    String name, sop, allowed, time, surl;

    SopModel()
    {

    }

    public SopModel(String name, String sop, String allowed, String time, String surl) {
        this.name = name;
        this.sop = sop;
        this.allowed = allowed;
        this.time = time;
        this.surl = surl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSop() {
        return sop;
    }

    public void setSop(String sop) {
        this.sop = sop;
    }

    public String getAllowed() {
        return allowed;
    }

    public void setAllowed(String allowed) {
        this.allowed = allowed;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

}
