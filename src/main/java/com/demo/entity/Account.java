package com.demo.entity;

import com.demo.entity.person.Person;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long autentificationId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId")
    private Person person;

    private String password;

    public Account(long autentificationId, Person person, String password) {
        this.autentificationId = autentificationId;
        this.person = person;
        this.password = password;
    }

    public Account() {
    }

    public long getAutentificationId() {
        return autentificationId;
    }

    public void setAutentificationId(long autentificationId) {
        this.autentificationId = autentificationId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
