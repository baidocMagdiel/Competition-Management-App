package com.demo.util;

import com.demo.entity.person.*;
import com.demo.util.exception.AppRequestException;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static com.demo.util.Constant.*;

/**
 * Clasa pentru crearea unei persoane in functie de tip
 */
public class PersonFactory {

    /**
     * Metoda pentru crearea unei persoane in functie de tip
     * @param personDto obiect ce contine datele persoanei
     * @return noua persoana creata
     */
    public Person createPerson(PersonDto personDto) {

        Person newPerson = null;
        String personType = personDto.getType();

        if (personType != null) {

            String surname = personDto.getSurname();
            String firstName = personDto.getFirstName();
            String address = personDto.getAddress();
            String gender = personDto.getGender();
            String email = personDto.getEmail();
            Date birthday = personDto.getBirthday();

            switch (personDto.getType() ) {
                case COACH:
                    newPerson = new Coach(0, surname, firstName, address, gender, birthday, email);
                    break;

                case ATHLETE:
                    newPerson = new Athlete(0, surname, firstName, address, gender, birthday, email, personDto.getWeight(), personDto.getBloodType(), personDto.getDanDegree(), personDto.getWorldRanking());
                    break;

                case COMPETITION_MANAGER:
                    newPerson = new CompetitionManager(0, surname, firstName, address, gender, birthday, email);
                    break;

                default:
                    throw new AppRequestException("Unknown/unsupported person-type: " + personType, HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new AppRequestException("Unknown/unsupported person-type", HttpStatus.BAD_REQUEST);
        }
        return newPerson;
    }
}
