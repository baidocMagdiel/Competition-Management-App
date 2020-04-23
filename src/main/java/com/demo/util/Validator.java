package com.demo.util;

import com.demo.entity.category.Category;
import com.demo.entity.person.Person;

import java.util.regex.Pattern;

import static com.demo.util.Constant.*;

public class Validator {

    public static String checkPerson(Person person){

        if(person.getFirstName().isEmpty() || person.getSurname().isEmpty() || person.getEmail().isEmpty() || person.getGender().isEmpty() || person.getAddress().isEmpty()){
            return EMPTY_FIELD;
        }

        if(checkEmail(person.getEmail()) == false){
            return EMAIL_INCORRECT;
        }
        return SUCCES;
    }

    public static boolean checkEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    public static String checkCategory(Category newCategory) {

        if(newCategory.getName().isEmpty() || newCategory.getAgeRange().isEmpty()){
            return EMPTY_FIELD;
        }
        return SUCCES;
    }
}
