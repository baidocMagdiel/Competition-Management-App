package com.demo.service;

import com.demo.entity.Club;
import com.demo.entity.person.Coach;
import com.demo.entity.person.Person;
import com.demo.entity.person.PersonDto;
import com.demo.repository.PersonRepository;
import com.demo.util.PersonFactory;
import com.demo.util.Validator;
import com.demo.util.exception.AppRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.demo.util.Constant.EXISTS;
import static com.demo.util.Constant.SUCCES;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ClubService clubService;

    private PersonFactory personFactory = new PersonFactory();


    public Person create(PersonDto personDto) {

        Person newPerson = personFactory.createPerson(personDto);
        Validator.checkPerson(newPerson);

        List<Person> allPersons = personRepository.findAll();
        for (Person p : allPersons) {
            if (p.getEmail().equals(newPerson.getEmail())) {
                throw new AppRequestException(EXISTS, HttpStatus.BAD_REQUEST);
            }
        }
        personRepository.save(newPerson);
        return newPerson;
    }

    /**
     *
     * @param personDto
     * @return
     */
    public Person updatePerson(PersonDto personDto) {

        Person person = personRepository.findByEmail(personDto.getEmail());

        if (person == null) {
            throw new AppRequestException("Person with email " + personDto.getEmail() + " not found.", HttpStatus.BAD_REQUEST);
        }

        Person newPerson = personFactory.createPerson(personDto);

        if (newPerson.getClass() != person.getClass())
            throw new AppRequestException("person does not have the same type.\"", HttpStatus.BAD_REQUEST);

        Validator.checkPerson(newPerson);

        newPerson.setPersonId(person.getPersonId());
        personRepository.save(newPerson);
        return newPerson;
    }

    /**
     * Metoda pentru stergerea unei persoane dupa email
     *
     * @param email adresa de email
     * @return persoana stearsa
     */
    public Person deleteByEmail(String email) {

        Person person = personRepository.findByEmail(email);
        if (person == null) {
            throw new AppRequestException("Person with email " + email + " not found.", HttpStatus.BAD_REQUEST);
        }
        personRepository.delete(person);
        return person;
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
     * @return clubul la care antrenorul a fost asignat
     */
    public Club addClub(long personId, long clubId) {

        Person person = personRepository.findById(personId).orElse(null);
        if (person == null) {
            throw new AppRequestException("Person with id " + personId + " not found.", HttpStatus.BAD_REQUEST);
        }

        Club club = clubService.findById(clubId);
        if (club == null) {
            throw new AppRequestException("Club with id " + clubId + " not found.", HttpStatus.BAD_REQUEST);
        }

        if (!(person instanceof Coach)) {
            throw new AppRequestException("The person must be a coach.", HttpStatus.BAD_REQUEST);
        }

        ((Coach) person).addClub(club);
        personRepository.save(person);
        return club;
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
