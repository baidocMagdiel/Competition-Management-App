package com.demo.entity.person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("CompMan")
public class CompetitionManager extends Person {

    public CompetitionManager(long personId, String surname, String firstName, String address, String gender, Date birthday, String email) {
        super(personId, surname, firstName, address, gender, birthday, email);
    }

    public CompetitionManager() {
    }
}
