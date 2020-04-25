package com.demo.util;

<<<<<<< HEAD
import com.demo.entity.Competition;
import com.demo.entity.Participation;
import com.demo.entity.category.Category;
import com.demo.entity.person.Person;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
=======
import com.demo.entity.category.Category;
import com.demo.entity.person.Person;

import java.util.regex.Pattern;

>>>>>>> d08b3eea09340023bd4cca200e3311a4099cadb2
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
<<<<<<< HEAD

    public static String checkCompetition(Competition newCompetition) {

        if(newCompetition.getFederation().isEmpty() || newCompetition.getPlace().isEmpty() || newCompetition.getName().isEmpty()){
            return EMPTY_FIELD;
        }

        if(newCompetition.getStartDate() == null || newCompetition.getEndDate() == null || newCompetition.getLastRegistrationDate() == null){
            return  EMPTY_FIELD;
        }

        if(newCompetition.getStartDate().after(newCompetition.getEndDate()) || newCompetition.getLastRegistrationDate().after(newCompetition.getStartDate())){
            return ERR_DATE_RANGE;
        }
        return SUCCES;
    }

    public static String checkParticipation(Participation participation){

        String[] ageRange = participation.getCategory().getAgeRange().split("-");
        Date currentTime = new Date();
        long diffInMillies = Math.abs(currentTime.getTime() - participation.getAthlete().getBirthday().getTime());
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(diffInMillies);
        int age = c.get(Calendar.YEAR);

        if(age < Integer.parseInt(ageRange[0]) || age > Integer.parseInt(ageRange[1])){
            return "[ERROR]:Athlete is incompatible for this category.Improper age.";
        }

        if(participation.getCompetition().getLastRegistrationDate().after(currentTime)){
            return "[ERROR]:Registration time is up.";
        }
        return SUCCES;
    }
=======
>>>>>>> d08b3eea09340023bd4cca200e3311a4099cadb2
}
