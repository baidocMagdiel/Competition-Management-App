package com.demo.entity;

public class AthleteInCompetition {

    private long id;
    private long competitionId;
    private long categoryId;
    private long athleteId;
    private int place;
    private int rankingPoints;

    public AthleteInCompetition(long id, long competitionId, long categoryId, long athleteId, int place, int rankingPoints) {
        this.id = id;
        this.competitionId = competitionId;
        this.categoryId = categoryId;
        this.athleteId = athleteId;
        this.place = place;
        this.rankingPoints = rankingPoints;
    }

    public AthleteInCompetition() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(long competitionId) {
        this.competitionId = competitionId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(long athleteId) {
        this.athleteId = athleteId;
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
}
