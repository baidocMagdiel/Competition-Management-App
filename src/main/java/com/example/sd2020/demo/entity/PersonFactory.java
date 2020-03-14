package com.example.sd2020.demo.entity;

public class PersonFactory {

    public Person getPerson(String personType){

        if(personType.equals("Athlete")){
                return new Athlete();
        }
        if(personType.equals("Coach")){
                return new Coach();
        }
        if(personType.equals("CompetitionManager")){
                return new CompetitionManager();
        }
        return null;
    }
}
