package com.demo.entity.person;

import com.demo.entity.Club;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("Coach")
public class Coach extends  Person{

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id")
    private Club club = new Club();

    public Coach(long personId, String surname, String firstName, String address, String gender, Date birthday, String email) {
        super(personId, surname, firstName, address, gender, birthday, email);
    }

    public Coach() {
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public void addClub(Club club){
        this.club = club;
    }

}
