package com.example.sd2020.demo.entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CompMan")
public class CompetitionManager extends Person {

    public CompetitionManager(long personId, String surname, String firstName, String address, String gender, String birthday, String email) {
        super(personId, surname, firstName, address, gender, birthday, email);
    }

    public CompetitionManager() {
    }
}
