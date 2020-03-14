package com.example.sd2020.demo.entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Coach")
public class Coach extends  Person{

    public String clubId;

    public Coach(long personId, String surname, String firstName, String address, String gender, String birthday, String email, String clubId) {
        super(personId, surname, firstName, address, gender, birthday, email);
        this.clubId = clubId;
    }

    public Coach() {
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }
}
