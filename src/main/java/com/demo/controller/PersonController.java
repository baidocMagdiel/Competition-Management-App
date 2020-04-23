package com.demo.controller;

import com.demo.entity.person.Person;
import com.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.demo.util.Constant.SUCCES;

@RestController
@RequestMapping(value="/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping(value="/create")
    @ResponseBody
    public ResponseEntity<String> create(@RequestParam String personType,
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

           String status = personService.create(personType, firstName, surname, address, birthday, email, gender, clubId, weight, danDegree, worldRanking, bloodType);
           if(!status.equals(SUCCES)){
               return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
           }
           return new ResponseEntity<>("[INFO]:The person has been added.", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<String> update(@RequestParam String personType,
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
                                         @RequestParam(defaultValue = "A0") String bloodType) {

        String status = personService.updatePerson(personType, firstName, surname, address, birthday, email, gender, clubId, weight, danDegree, worldRanking, bloodType);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:The person has been updated.", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam String email) {
        String status = personService.deleteByEmail(email);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:The person has been deleted.", HttpStatus.OK);
    }

    @GetMapping(value = "/getall")
    @ResponseBody
    public List<Person> getAll(){
        return personService.findAll();
    }
}
