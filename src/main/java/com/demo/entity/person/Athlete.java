package com.demo.entity.person;

import com.demo.entity.Competition;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("Athlete")
public class Athlete extends Person {

    private double weight;
    private String bloodType;
    private String DanDegree;
    private int worldRanking;

    public Athlete(long personId, String surname, String firstName, String address, String gender, Date birthday, String email, double weight, String bloodType, String danDegree, int worldRanking) {
        super(personId, surname, firstName, address, gender, birthday, email);
        this.weight = weight;
        this.bloodType = bloodType;
        DanDegree = danDegree;
        this.worldRanking = worldRanking;
    }

    public Athlete() {
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
        return DanDegree;
    }

    public void setDanDegree(String danDegree) {
        DanDegree = danDegree;
    }

    public int getWorldRanking() {
        return worldRanking;
    }

    public void setWorldRanking(int worldRanking) {
        this.worldRanking = worldRanking;
    }

}
