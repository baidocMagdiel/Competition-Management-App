package com.demo.entity;

public class Autentification {

    private long autentificationId;
    private String email;
    private String password;

    public Autentification(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Autentification() {
    }

    public long getAutentificationId() {
        return autentificationId;
    }

    public void setAutentificationId(long autentificationId) {
        this.autentificationId = autentificationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
