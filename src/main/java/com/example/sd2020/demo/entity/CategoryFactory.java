package com.example.sd2020.demo.entity;

public class CategoryFactory {

    public Category getCategory(String categoryType){

        if(categoryType.equals("Single")){
            return new SingleCategory();
        }
        if(categoryType.equals("Team")){
            return new TeamCategory();
        }
        return null;
    }
}
