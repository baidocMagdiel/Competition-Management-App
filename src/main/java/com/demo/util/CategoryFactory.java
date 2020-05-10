package com.demo.util;

import com.demo.entity.category.Category;
import com.demo.entity.category.CategoryDto;
import com.demo.entity.category.SingleCategory;
import com.demo.entity.category.TeamCategory;
import com.demo.util.exception.AppRequestException;
import org.springframework.http.HttpStatus;

import static com.demo.util.Constant.INDIVIDUAL;
import static com.demo.util.Constant.TEAM;

/**
 * Clasa pentru crearea unei categorii in functie de tip
 */
public class CategoryFactory {


    public Category createCategory(CategoryDto categoryDto) {

        Category newCategory = null;
        String type = categoryDto.getType();
        if (type != null) {

            String name = categoryDto.getName();
            String ageRange = categoryDto.getAgeRange();
            String gender = categoryDto.getGender();
            String categoryType = categoryDto.getCategoryType();
            float matchTime = categoryDto.getMatchTime();
            int noOfTeamMembers = categoryDto.getNoOfTeamMembers();
            int noOfMatches = categoryDto.getNoOfMatches();
            String weightRange = categoryDto.getWeightRange();

            switch (type) {
                case TEAM:
                    newCategory = new TeamCategory(0, name, ageRange, gender, categoryType, matchTime, noOfTeamMembers, noOfMatches);
                    break;

                case INDIVIDUAL:
                    newCategory = new SingleCategory(0, name, ageRange, gender, categoryType, matchTime, weightRange);
                    break;
                default:
                    throw new AppRequestException("Unknown/unsupported categoty-type: " + type, HttpStatus.BAD_REQUEST);
            }

        } else {
            throw new AppRequestException("Unknown/unsupported person-type", HttpStatus.BAD_REQUEST);
        }
        return newCategory;
    }
}
