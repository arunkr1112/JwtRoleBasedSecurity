package com.example.demo.student;

public class MyResponseObject {
    private int status;
    private String jwt;

    public MyResponseObject() {
    }

    public MyResponseObject(int status, String jwt) {
        this.status = status;
        this.jwt = jwt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
