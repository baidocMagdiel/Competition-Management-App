package com.demo.entity;

public class Category {

    private String dtype;
    private long categoryId;
    private String name;
    private String ageRange;
    private float matchTime;
    private String weightRange;
    private int noOfTeamMembers;
    private int noOfMatches;

    public Category(String dtype, long categoryId, String name, String ageRange, float matchTime, String weightRange, int noOfTeamMembers, int noOfMatches) {
        this.dtype = dtype;
        this.categoryId = categoryId;
        this.name = name;
        this.ageRange = ageRange;
        this.matchTime = matchTime;
        this.weightRange = weightRange;
        this.noOfTeamMembers = noOfTeamMembers;
        this.noOfMatches = noOfMatches;
    }

    public Category() {
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public float getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(float matchTime) {
        this.matchTime = matchTime;
    }

    public String getDtype() { return dtype; }

    public void setDtype(String dtype) { this.dtype = dtype;}

    public String getWeightRange() {
        return weightRange;
    }

    public void setWeightRange(String weightRange) {
        this.weightRange = weightRange;
    }

    public int getNoOfTeamMembers() {
        return noOfTeamMembers;
    }

    public void setNoOfTeamMembers(int noOfTeamMembers) {
        this.noOfTeamMembers = noOfTeamMembers;
    }

    public int getNoOfMatches() {
        return noOfMatches;
    }

    public void setNoOfMatches(int noOfMatches) {
        this.noOfMatches = noOfMatches;
    }
}
