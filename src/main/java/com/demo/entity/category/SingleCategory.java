package com.demo.entity.category;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Single")
    public class SingleCategory extends Category {
    private String weightRange;

    public SingleCategory(long categoryId, String name, String ageRange, String gender, String categoryType, float matchTime, String weightRange) {
        super(categoryId, name, ageRange, gender, categoryType, matchTime);
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
