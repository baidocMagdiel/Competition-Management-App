package com.demo.entity.category;

public class CategoryDto {

    private long id;
    private String type;
    private String name;
    private String ageRange;
    private String gender;
    private String categoryType;
    private float matchTime;
    private String weightRange;
    private int noOfTeamMembers;
    private int noOfMatches;

    public CategoryDto(long id, String type, String name, String ageRange, String gender, String categoryType, float matchTime, String weightRange, int noOfTeamMembers, int noOfMatches) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.ageRange = ageRange;
        this.gender = gender;
        this.categoryType = categoryType;
        this.matchTime = matchTime;
        this.weightRange = weightRange;
        this.noOfTeamMembers = noOfTeamMembers;
        this.noOfMatches = noOfMatches;
    }

    public CategoryDto() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public float getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(float matchTime) {
        this.matchTime = matchTime;
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
