package com.demo.entity.category;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Team")
public class TeamCategory extends Category {
    private int noOfTeamMembers;
    private int noOfMatches;

    public TeamCategory(long categoryId, String name, String ageRange, float matchTime, int noOfTeamMembers, int noOfMatches) {
        super(categoryId, name, ageRange, matchTime);
        this.noOfTeamMembers = noOfTeamMembers;
        this.noOfMatches = noOfMatches;
    }

    public TeamCategory() {
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

    @Override
    public float computeMatchTime(){
        return this.getMatchTime() * noOfMatches;
    }
}
