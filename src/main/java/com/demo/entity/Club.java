package com.demo.entity;

import com.demo.entity.person.Athlete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String clubId;
    private String name;
    private String address;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Athlete> athletes = new ArrayList<>();

    public Club(long id, String clubId, String name, String address) {
        this.id = id;
        this.clubId = clubId;
        this.name = name;
        this.address = address;
    }

    public Club() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthlete(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    public void addAthlete(Athlete athlete) {
        athletes.add(athlete);
    }

    public void removeAthlete(Athlete athlete) {
        athletes.remove(athlete);
    }
}
