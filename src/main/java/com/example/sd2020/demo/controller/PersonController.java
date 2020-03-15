package com.example.sd2020.demo.controller;

import com.example.sd2020.demo.entity.*;
import com.example.sd2020.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/person")
public class PersonController {
    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping(value="/create")
    @ResponseBody
    public List<Person> create(@RequestParam String personType,
                               @RequestParam String firstName,
                               @RequestParam String surname,
                               @RequestParam String address,
                               @RequestParam String birthday,
                               @RequestParam String email,
                               @RequestParam String gender,
                               @RequestParam(defaultValue = "0") String clubId,
                               @RequestParam(defaultValue = "1.5") double weight,
                               @RequestParam(defaultValue = "10 Kyu") String danDegree,
                               @RequestParam(defaultValue = "0") int worldRanking,
                               @RequestParam(defaultValue = "A0") String bloodType){

        PersonFactory personFactory = new PersonFactory();
        Person newPerson = personFactory.getPerson(personType);

        newPerson.setFirstName(firstName);
        newPerson.setSurname(surname);
        newPerson.setAddress(address);
        newPerson.setBirthday(birthday);
        newPerson.setEmail(email);
        newPerson.setGender(gender);

        if(newPerson instanceof Coach){
            ((Coach) newPerson).setClubId(clubId);
        }
        if(newPerson instanceof Athlete){
            ((Athlete) newPerson).setClubId(clubId);
            ((Athlete) newPerson).setBloodType(bloodType);
            ((Athlete) newPerson).setWeight(weight);
            ((Athlete) newPerson).setDanDegree(danDegree);
            ((Athlete) newPerson).setBloodType(bloodType);
            ((Athlete) newPerson).setWorldRanking(worldRanking);
        }
        
        personRepository.save(newPerson);
        return personRepository.findAll();
    }

    @GetMapping(value="/deleteAll")
    public String deleteAll(){
        try{
            personRepository.deleteAll();
        } catch(Exception ex){
            return "[Error] The records were not deleted. " + ex.getMessage();
        }
        return "The records have been deleted.";
    }

    @GetMapping(value="/findAll")
    public List<Person> findAll(){
        return personRepository.findAll();
    }

}
