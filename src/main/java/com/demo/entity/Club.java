package com.demo.entity;

public class Club {

    private long id;
    private String address;
    private String clubId;
    private String name;

    public Club(long id, String clubId, String name, String address) {
        this.id = id;
        this.clubId = clubId;
        this.name = name;
        this.address = address;
    }

    public Club() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
