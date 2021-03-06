package com.demo.entity.person;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long personId;
    private String surname;
    private String firstName;
    private String address;
    private String gender;
    private Date birthday;
    private String email;

    public Person(long personId, String surname, String firstName, String address, String gender, Date birthday, String email) {
        this.personId = personId;
        this.surname = surname;
        this.firstName = firstName;
        this.address = address;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
    }

    public Person() {
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
