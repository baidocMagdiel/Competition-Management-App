package com.demo.service;

import com.demo.entity.Club;
import com.demo.entity.person.Coach;
import com.demo.entity.person.Person;
import com.demo.repository.PersonRepository;
import com.demo.util.PersonFactory;
import com.demo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.demo.util.Constant.*;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ClubService clubService;

    private PersonFactory personFactory = new PersonFactory();

    /**
     * Metoda pentru crearea unei persoane
     *
     * @param personType   tipul persoanei
     * @param firstName    prenume
     * @param surname      nume de familie
     * @param address      adresa
     * @param birthdayStr  data de nastere
     * @param email        email
     * @param gender       sex
     * @param weight       greutate
     * @param danDegree    grad
     * @param worldRanking loc in clasamentul mondial
     * @param bloodType    grupa sanguina
     * @return SUCCES sau mesaj de eroare
     */
    public String create(String personType,
                         String firstName,
                         String surname,
                         String address,
                         String birthdayStr,
                         String email,
                         String gender,
                         double weight,
                         String danDegree,
                         int worldRanking,
                         String bloodType) {

        SimpleDateFormat dateformat = new SimpleDateFormat(DATE_FORMAT);
        Date birthday = null;
        try {
            birthday = dateformat.parse(birthdayStr);
        } catch (ParseException e) {
            return ERR_DATE_FORMAT;
        }

        Person newPerson = personFactory.createPerson(personType, firstName, surname, address, birthday, email, gender, weight, danDegree, worldRanking, bloodType);
        if (newPerson == null) return "[ERROR]:Unknown/unsupported person-type [" + personType + "]";

        String flag = Validator.checkPerson(newPerson);
        if (!flag.equals(SUCCES)) return flag;

        List<Person> allPersons = personRepository.findAll();
        for (Person p : allPersons) {
            if (p.getEmail().equals(newPerson.getEmail())) {
                return EXISTS + newPerson.getEmail();
            }
        }
        personRepository.save(newPerson);
        return SUCCES;
    }

    /**
     * Metoda pentru actualizarea unei persoane
     *
     * @param personType   tipul persoanei
     * @param firstName    prenume
     * @param surname      nume de familie
     * @param address      adresa
     * @param birthdayStr  data de nastere
     * @param email        email
     * @param gender       sex
     * @param weight       greutate
     * @param danDegree    grad
     * @param worldRanking loc in clasamentul mondial
     * @param bloodType    grupa sanguina
     * @return SUCCES sau mesaj de eroare
     */
    public String updatePerson(String personType,
                               String firstName,
                               String surname,
                               String address,
                               String birthdayStr,
                               String email,
                               String gender,
                               double weight,
                               String danDegree,
                               int worldRanking,
                               String bloodType) {

        Person person = personRepository.findByEmail(email);

        if (person == null) {
            return "Person with email " + email + " not found.";
        }

        SimpleDateFormat dateformat = new SimpleDateFormat(DATE_FORMAT);
        Date birthday = null;
        try {
            birthday = dateformat.parse(birthdayStr);
        } catch (ParseException e) {
            return ERR_DATE_FORMAT;
        }

        Person newPerson = personFactory.createPerson(personType, firstName, surname, address, birthday, email, gender, weight, danDegree, worldRanking, bloodType);
        if (newPerson == null) return "[ERROR]:Unknown/unsupported person-type [" + personType + "].";

        if (newPerson.getClass() != person.getClass()) return "[ERROR]:The person does not have the same type.";

        String flag = Validator.checkPerson(newPerson);
        if (!flag.equals(SUCCES)) return flag;

        newPerson.setPersonId(person.getPersonId());
        personRepository.save(newPerson);
        return SUCCES;
    }

    /**
     * Metoda pentru stergerea unei persoane dupa email
     *
     * @param email adresa de email
     * @return SUCCES sau mesaj de eroare
     */
    public String deleteByEmail(String email) {

        Person person = personRepository.findByEmail(email);
        if (person == null) {
            return "Person with email " + email + " not found.";
        }
        personRepository.delete(person);
        return SUCCES;
    }

    /**
     * Metoda pentru stergerea unei persoane dupa id
     *
     * @param personId id-ul persoanei
     * @return SUCCES sau mesaj de eroare
     */
    public String deleteById(long personId) {

        Person person = personRepository.findById(personId).orElse(null);
        if (person == null) {
            return "Person with id " + personId + " not found.";
        }
        personRepository.delete(person);
        return SUCCES;
    }

    /**
     * Asignarea unui antrenor la un club
     *
     * @param personId id-ul antrenorului
     * @param clubId   id-ul clubului
     * @return SUCCES sau mesaj de eroare
     */
    public String addClub(long personId, long clubId) {

        Person person = personRepository.findById(personId).orElse(null);
        if (person == null) {
            return "Person with id " + personId + " not found.";
        }

        Club club = clubService.findById(clubId);
        if (club == null) {
            return "Club with id " + clubId + " not found.";
        }

        if (!(person instanceof Coach)) {
            return "[ERROR]:The person must be a coach.";
        }

        ((Coach) person).addClub(club);
        personRepository.save(person);
        return SUCCES;
    }

    /**
     * Metoda pentru determinarea tuturor persoanelor din baza de date
     *
     * @return lista persoanelor
     */
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    /**
     * Metoda pentru cautarea unei persoane dupa id
     *
     * @param personId id-ul peroanei
     * @return perosana sau null
     */
    public Person findById(long personId) {
        return personRepository.findById(personId).orElse(null);
    }

    /**
     * Metoda pentru cautarea unei persoane dupa email
     *
     * @param email adresa de email
     * @return perosana sau null
     */
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

}
