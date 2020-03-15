package com.example.sd2020.demo.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Single")
public class SingleCategory extends Category {
    private String weightRange;

    public SingleCategory(long categoryId, String name, String ageRange, float matchTime, String weightRange) {
        super(categoryId, name, ageRange, matchTime);
        this.weightRange = weightRange;
    }

    public SingleCategory() {
    }

    public String getWeightRange() {
        return weightRange;
    }

    public void setWeightRange(String weightRange) {
        this.weightRange = weightRange;
    }
}
