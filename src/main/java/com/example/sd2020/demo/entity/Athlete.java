package com.example.sd2020.demo.entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Athlete")
public class Athlete extends Person {

    private double weight;
    private String bloodType;
    private String DanDegree;
    private int worldRanking;
    private String clubId;

    public Athlete(long personId, String surname, String firstName, String address, String gender, String birthday, String email, double weight, String bloodType, String danDegree, int worldRanking, String clubId) {
        super(personId, surname, firstName, address, gender, birthday, email);
        this.weight = weight;
        this.bloodType = bloodType;
        DanDegree = danDegree;
        this.worldRanking = worldRanking;
        this.clubId = clubId;
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

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }
}
