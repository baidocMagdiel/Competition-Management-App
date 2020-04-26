package com.demo.util;

import com.demo.entity.person.Athlete;
import com.demo.entity.person.Coach;
import com.demo.entity.person.CompetitionManager;
import com.demo.entity.person.Person;

import java.util.Date;

import static com.demo.util.Constant.*;

/**
 * Clasa pentru crearea unei persoane in functie de tip
 */
public class PersonFactory {

    /**
     * Metoda pentru crearea unei persoane in functie de tip
     *
     * @param personType   tipul persoanei
     * @param firstName    prenume
     * @param surname      nume de familie
     * @param address      adresa
     * @param birthday     data de nastere
     * @param email        email
     * @param gender       sex
     * @param weight       greutate
     * @param danDegree    grad
     * @param worldRanking loc in clasamentul mondial
     * @param bloodType    grupa sanguina
     * @return persoana sau null
     */
    public Person createPerson(String personType,
                               String firstName,
                               String surname,
                               String address,
                               Date birthday,
                               String email,
                               String gender,
                               double weight,
                               String danDegree,
                               int worldRanking,
                               String bloodType) {

        Person newPerson = null;
        if (personType != null) {

            switch (personType) {
                case COACH:
                    newPerson = new Coach(0, surname, firstName, address, gender, birthday, email);
                    break;

                case ATHLETE:
                    newPerson = new Athlete(0, surname, firstName, address, gender, birthday, email, weight, bloodType, danDegree, worldRanking);
                    break;

                case COMPETITION_MANAGER:
                    newPerson = new CompetitionManager(0, surname, firstName, address, gender, birthday, email);
                    break;

                default:
                    break;
            }
        }
        return newPerson;
    }
}
