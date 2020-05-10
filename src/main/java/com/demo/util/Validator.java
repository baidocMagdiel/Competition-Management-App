package com.demo.util;

import com.demo.entity.Club;
import com.demo.entity.Competition;
import com.demo.entity.Participation;
import com.demo.entity.category.Category;
import com.demo.entity.category.SingleCategory;
import com.demo.entity.category.TeamCategory;
import com.demo.entity.person.Athlete;
import com.demo.entity.person.Person;
import com.demo.util.exception.AppRequestException;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.demo.util.Constant.*;

public class Validator {

    public static boolean checkPerson(Person person) {

        if (person.getFirstName().isEmpty() || person.getSurname().isEmpty() || person.getEmail().isEmpty() || person.getGender().isEmpty() || person.getAddress().isEmpty()) {
            throw new AppRequestException(EMPTY_FIELD, HttpStatus.BAD_REQUEST);
        }

        if (checkEmail(person.getEmail()) == false) {
            throw new AppRequestException(EMAIL_INCORRECT, HttpStatus.BAD_REQUEST);
        }
        return true;
    }

    public static boolean checkEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    public static boolean checkCategory(Category newCategory) {

        if (newCategory.getName().isEmpty() || newCategory.getAgeRange().isEmpty()) {
            throw new AppRequestException(EMPTY_FIELD, HttpStatus.BAD_REQUEST);

        }
        return true;
    }

    public static String checkCompetition(Competition newCompetition) {

        if (newCompetition.getFederation().isEmpty() || newCompetition.getPlace().isEmpty() || newCompetition.getName().isEmpty()) {
            throw new AppRequestException(EMPTY_FIELD, HttpStatus.BAD_REQUEST);
        }

        if (newCompetition.getStartDate() == null || newCompetition.getEndDate() == null || newCompetition.getLastRegistrationDate() == null) {
            throw new AppRequestException(EMPTY_FIELD, HttpStatus.BAD_REQUEST);
        }

        if (newCompetition.getStartDate().after(newCompetition.getEndDate()) || newCompetition.getLastRegistrationDate().after(newCompetition.getStartDate())) {
            throw new AppRequestException(ERR_DATE_RANGE, HttpStatus.BAD_REQUEST);
        }
        return SUCCES;
    }

    public static String checkParticipation(Participation participation) {

        Date currentTime = new Date();
        if (participation.getCompetition().getLastRegistrationDate().after(currentTime)) {
            return "[ERROR]:Registration time is up.";
        }

        if (participation.getCategory() instanceof TeamCategory) {
            if (((TeamCategory) participation.getCategory()).getNoOfTeamMembers() != participation.getAthlete().size()) {
                return "[ERROR]:Your team must contain " + ((TeamCategory) participation.getCategory()).getNoOfTeamMembers() + " members, not " + participation.getAthlete().size();
            }

            if (participation.getName().isEmpty()) {
                return "[ERROR]:Your team must have a name.";
            }
        }

        if (participation.getCategory() instanceof SingleCategory) {
            if (participation.getAthlete().size() != 1) {
                return "[ERROR]:Only one athlete registers for the individual category.";
            }

            if (participation.getCategory().getCategoryType().equals(KUMITE)) {
                String[] weightRange = ((SingleCategory) participation.getCategory()).getWeightRange().split("-");
                if (participation.getAthlete().get(0).getWeight() < Double.parseDouble(weightRange[0]) || participation.getAthlete().get(0).getWeight() > Double.parseDouble(weightRange[1])) {
                    return "[ERROR]:Athlete is incompatible for this category.Improper weight.";
                }
            }

            if (!participation.getAthlete().get(0).getGender().equals(participation.getCategory().getGender())) {
                return "[ERROR]:Athlete is incompatible for this category.Improper gender.";
            }
        }

        String[] ageRange = participation.getCategory().getAgeRange().split("-");
        for (Athlete ath : participation.getAthlete()) {
            long diffInMillies = Math.abs(currentTime.getTime() - ath.getBirthday().getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            long age = diff / 365;

            if (age < Integer.parseInt(ageRange[0]) || age > Integer.parseInt(ageRange[1])) {
                return "[ERROR]:Athlete " + ath.getFirstName() + " " + ath.getSurname() + " is incompatible for this category.Improper age. ";
            }

            if (!ath.getGender().equals(participation.getCategory().getGender())) {
                return "[ERROR]:Athlete is incompatible for this category.Improper gender.";
            }
        }
        return SUCCES;
    }

    public static String checkClub(Club newClub) {
        if (newClub.getAddress().isEmpty() || newClub.getName().isEmpty() || newClub.getClubId().isEmpty()) {
            return EMPTY_FIELD;
        }
        return SUCCES;
    }

    public static boolean checkPassword(String password) {

        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=-])(?=\\S+$).{8,}$";
        Pattern pat = Pattern.compile(passwordRegex);
        return pat.matcher(password).matches();
    }
}
