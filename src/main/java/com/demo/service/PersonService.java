package com.demo.service;

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

    private PersonFactory personFactory = new PersonFactory();

    public String create(String personType,
                         String firstName,
                         String surname,
                         String address,
                         String birthdayStr,
                         String email,
                         String gender,
                         String clubId,
                         double weight,
                         String danDegree,
                         int worldRanking,
                         String bloodType){

        SimpleDateFormat dateformat = new SimpleDateFormat(DATE_FORMAT);
        Date birthday = null;
        try {
            birthday = dateformat.parse(birthdayStr);
        } catch (ParseException e) {
<<<<<<< HEAD
            return ERR_DATE_FORMAT;
=======
            return ERR_BIRTHDAY_FORMAT;
>>>>>>> d08b3eea09340023bd4cca200e3311a4099cadb2
        }

        Person newPerson  = personFactory.createPerson(personType, firstName, surname, address, birthday, email, gender, clubId, weight, danDegree, worldRanking, bloodType);
        if(newPerson == null) return "[ERROR]:Unknown/unsupported person-type [" + personType + "]" ;

        String flag = Validator.checkPerson(newPerson);
        if(!flag.equals(SUCCES)) return flag;

        List<Person> allPersons = personRepository.findAll();
        for(Person p : allPersons){
            if(p.getEmail().equals(newPerson.getEmail())){
                return EXISTS + newPerson.getEmail();
            }
        }

        personRepository.save(newPerson);
        return SUCCES;
    }

    public String updatePerson(String personType,
                               String firstName,
                               String surname,
                               String address,
                               String birthdayStr,
                               String email,
                               String gender,
                               String clubId,
                               double weight,
                               String danDegree,
                               int worldRanking,
                               String bloodType){

        Person person = personRepository.findByEmail(email);

        if (person == null){
            return "Person with email " + email + " not found.";
        }

        SimpleDateFormat dateformat = new SimpleDateFormat(DATE_FORMAT);
        Date birthday = null;
        try {
            birthday = dateformat.parse(birthdayStr);
        } catch (ParseException e) {
<<<<<<< HEAD
            return ERR_DATE_FORMAT;
=======
            return ERR_BIRTHDAY_FORMAT;
>>>>>>> d08b3eea09340023bd4cca200e3311a4099cadb2
        }

        Person newPerson  = personFactory.createPerson(personType, firstName, surname, address, birthday, email, gender, clubId, weight, danDegree, worldRanking, bloodType);
        if(newPerson == null) return "[ERROR]:Unknown/unsupported person-type [" + personType + "]." ;

        if(newPerson.getClass() != person.getClass()) return "[ERROR]:The person does not have the same type.";

        String flag = Validator.checkPerson(newPerson);
        if(!flag.equals(SUCCES)) return flag;

        newPerson.setPersonId(person.getPersonId());
        personRepository.save(newPerson);
        return SUCCES;
    }

    public String deleteByEmail(String email){

       Person person = personRepository.findByEmail(email);
        if (person == null){
            return "Person with email " + email + " not found.";
        }
        personRepository.delete(person);
        return SUCCES;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }
<<<<<<< HEAD

    public Person findById(long personId){
        return personRepository.findById(personId).orElse(null);
    }
=======
>>>>>>> d08b3eea09340023bd4cca200e3311a4099cadb2
}
