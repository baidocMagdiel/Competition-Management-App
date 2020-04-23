package com.demo.util;

import com.demo.entity.category.Category;
import com.demo.entity.category.SingleCategory;
import com.demo.entity.category.TeamCategory;
import static com.demo.util.Constant.*;

public class CategoryFactory {

    public Category createCategory(String categoryType,
                                   String name,
                                   String ageRange,
                                   float matchTime,
                                   String weightRange,
                                   int noOfTeamMembers,
                                   int noOfMatches){

        Category newCategory = null;
        if(categoryType != null){

            switch (categoryType){
                case TEAM:
                    newCategory = new TeamCategory(0,name,ageRange,matchTime,noOfTeamMembers,noOfMatches);
                    break;

                case INDIVIDUAL:
                    newCategory = new SingleCategory(0,name,ageRange,matchTime,weightRange);
                    break;
                default:
                    break;
            }
        }

        return newCategory;
    }
}
