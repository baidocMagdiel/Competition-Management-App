package com.demo.entity.category;

import javax.persistence.*;

@Entity
@Table(name = "Category")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long categoryId;
    private String name;
    private String ageRange;
    private String gender;
    private String categoryType;
    private float matchTime;

    public Category(long categoryId, String name, String ageRange, String gender, String categoryType, float matchTime) {
        this.categoryId = categoryId;
        this.name = name;
        this.ageRange = ageRange;
        this.gender = gender;
        this.categoryType = categoryType;
        this.matchTime = matchTime;
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

    public float computeMatchTime(){
        return matchTime;
    }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getCategoryType() { return categoryType; }

    public void setCategoryType(String categoryType) { this.categoryType = categoryType;}
}
