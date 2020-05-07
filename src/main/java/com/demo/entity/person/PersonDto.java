package com.demo.entity.person;

import java.util.Date;

public class PersonDto {

    private String type;
    private long id;
    private String address;
    private String surname;
    private String firstName;
    private String gender;
    private Date birthday;
    private String email;
    private double weight;
    private String bloodType;
    private String danDegree;
    private int worldRanking;

    public PersonDto(String type, long id, String address, String surname, String firstName, String gender, Date birthday, String email, double weight, String bloodType, String danDegree, int worldRanking) {
        this.type = type;
        this.id = id;
        this.address = address;
        this.surname = surname;
        this.firstName = firstName;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.weight = weight;
        this.bloodType = bloodType;
        this.danDegree = danDegree;
        this.worldRanking = worldRanking;
    }

    public PersonDto() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
