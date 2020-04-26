package com.demo.util;

import com.demo.entity.category.Category;
import com.demo.entity.category.SingleCategory;
import com.demo.entity.category.TeamCategory;

import static com.demo.util.Constant.INDIVIDUAL;
import static com.demo.util.Constant.TEAM;

/**
 * Clasa pentru crearea unei categorii in functie de tip
 */
public class CategoryFactory {

    /**
     * Metoda pentru crearea unei categorii
     *
     * @param categoryType    tipul categoriei
     * @param name            numele
     * @param ageRange        categorie de varsta
     * @param gender          gen(sex)
     * @param catType         gen categorie
     * @param matchTime       durata meci
     * @param weightRange     grupa de greutate
     * @param noOfTeamMembers numarul membrilor din echipa
     * @param noOfMatches     numarul de meciuri
     * @return categora sau null
     */
    public Category createCategory(String categoryType,
                                   String name,
                                   String ageRange,
                                   String gender,
                                   String catType,
                                   float matchTime,
                                   String weightRange,
                                   int noOfTeamMembers,
                                   int noOfMatches) {

        Category newCategory = null;
        if (categoryType != null) {

            switch (categoryType) {
                case TEAM:
                    newCategory = new TeamCategory(0, name, ageRange, gender, catType, matchTime, noOfTeamMembers, noOfMatches);
                    break;

                case INDIVIDUAL:
                    newCategory = new SingleCategory(0, name, ageRange, gender, catType, matchTime, weightRange);
                    break;
                default:
                    break;
            }
        }

        return newCategory;
    }
}
