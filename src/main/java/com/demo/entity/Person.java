package com.demo.entity;

public class Person {

    private String dtype;
    private long personId;
    private String surname;
    private String firstName;
    private String address;
    private String gender;
    private String birthday;
    private String email;
    public String clubId;
    private double weight;
    private String bloodType;
    private String danDegree;
    private int worldRanking;

    public Person(String dtype, long personId, String surname, String firstName, String address, String gender, String birthday, String email, String clubId, double weight, String bloodType, String danDegree, int worldRanking) {
        this.dtype = dtype;
        this.personId = personId;
        this.surname = surname;
        this.firstName = firstName;
        this.address = address;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.clubId = clubId;
        this.weight = weight;
        this.bloodType = bloodType;
        this.danDegree = danDegree;
        this.worldRanking = worldRanking;
    }

    public Person() {
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getDanDegree() {
        return danDegree;
    }

    public void setDanDegree(String danDegree) {
        this.danDegree = danDegree;
    }

    public int getWorldRanking() {
        return worldRanking;
    }

    public void setWorldRanking(int worldRanking) {
        this.worldRanking = worldRanking;
    }
}
