package com.demo.controller;

import com.demo.entity.Person;
import com.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/person")
public class PersonController {
    private PersonRepository personRepository;

    @Autowired
    public PersonController() {
        this.personRepository = new PersonRepository();
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

        Person newPerson = new Person();

        newPerson.setDtype(personType);
        newPerson.setFirstName(firstName);
        newPerson.setSurname(surname);
        newPerson.setAddress(address);
        newPerson.setBirthday(birthday);
        newPerson.setEmail(email);
        newPerson.setGender(gender);

        if(personType.equals("Coach")){
            newPerson.setClubId(clubId);
        }
        if(personType.equals("Athlete")){
            newPerson.setClubId(clubId);
            newPerson.setBloodType(bloodType);
            newPerson.setWeight(weight);
            newPerson.setDanDegree(danDegree);
            newPerson.setBloodType(bloodType);
            newPerson.setWorldRanking(worldRanking);
        }

        personRepository.insert(newPerson);
        return personRepository.findAll("*");
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

        return personRepository.findAll("*");
    }

}
