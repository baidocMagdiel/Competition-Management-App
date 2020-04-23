package com.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Competition")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long competitionId;
    private String name;
    private Date startDate;
    private Date endDate;
    private Date lastRegistrationDate;
    private String place;
    private String federation;
    private int noOfEntries;
    private int noOfCountries;
    private String competitionStatus;

    public Competition(long competitionId, String name, Date startDate, Date endDate, Date lastRegistrationDate, String place, String federation, int noOfEntries, int noOfCountries, String competitionStatus) {
        this.competitionId = competitionId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lastRegistrationDate = lastRegistrationDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getLastRegistrationDate() {
        return lastRegistrationDate;
    }

    public void setLastRegistrationDate(Date lastRegistrationDate) {
        this.lastRegistrationDate = lastRegistrationDate;
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
