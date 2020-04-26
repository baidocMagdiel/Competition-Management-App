package com.demo.entity;

import com.demo.entity.category.Category;
import com.demo.entity.person.Athlete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Participation")
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Competition competition;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Category category;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Athlete> athlete;

    private int place;
    private int rankingPoints;

    public Participation(long id, String name, Competition competition, Category category, List<Athlete> athlete, int place, int rankingPoints) {
        this.id = id;
        this.name = name;
        this.competition = competition;
        this.category = category;
        this.athlete = athlete;
        this.place = place;
        this.rankingPoints = rankingPoints;
    }

    public Participation() {
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getRankingPoints() {
        return rankingPoints;
    }

    public void setRankingPoints(int rankingPoints) {
        this.rankingPoints = rankingPoints;
    }

    public List<Athlete> getAthlete() { return athlete; }

    public void setAthlete(List<Athlete> athlete) { this.athlete = athlete; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
