package com.demo.entity;

public class Competition {

    private long competitionId;
    private String name;
    private String date;
    private String place;
    private String federation;
    private int noOfEntries;
    private int noOfCountries;
    private String competitionStatus;

    public Competition(long competitionId, String name, String date, String place, String federation, int noOfEntries, int noOfCountries, String competitionStatus) {
        this.competitionId = competitionId;
        this.name = name;
        this.date = date;
        this.place = place;
        this.federation = federation;
        this.noOfEntries = noOfEntries;
        this.noOfCountries = noOfCountries;
        this.competitionStatus = competitionStatus;
    }

    public Competition() {
    }

    public long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(long competitionId) {
        this.competitionId = competitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFederation() {
        return federation;
    }

    public void setFederation(String federation) {
        this.federation = federation;
    }

    public int getNoOfEntries() {
        return noOfEntries;
    }

    public void setNoOfEntries(int noOfEntries) {
        this.noOfEntries = noOfEntries;
    }

    public int getNoOfCountries() {
        return noOfCountries;
    }

    public void setNoOfCountries(int noOfCountries) {
        this.noOfCountries = noOfCountries;
    }

    public String getCompetitionStatus() {
        return competitionStatus;
    }

    public void setCompetitionStatus(String competitionStatus) {
        this.competitionStatus = competitionStatus;
    }
}
