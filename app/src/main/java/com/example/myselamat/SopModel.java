package com.example.myselamat;

public class SopModel {

    String name, sop, allowed, disallowed, surl;

    SopModel()
    {

    }

    public SopModel(String name, String sop, String allowed, String disallowed, String surl) {
        this.name = name;
        this.sop = sop;
        this.allowed = allowed;
        this.disallowed = disallowed;
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

    public String getDisallowed() {
        return disallowed;
    }

    public void setDisallowed(String disallowed) { this.disallowed = disallowed;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

}
