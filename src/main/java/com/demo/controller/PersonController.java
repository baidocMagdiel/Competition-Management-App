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
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * Metoda pentru crearea unei persoane
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
     * @return mesaj corespunzator
     */
    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<String> create(@RequestParam String personType,
                                         @RequestParam String firstName,
                                         @RequestParam String surname,
                                         @RequestParam String address,
                                         @RequestParam String birthday,
                                         @RequestParam String email,
                                         @RequestParam String gender,
                                         @RequestParam(defaultValue = "1.5") double weight,
                                         @RequestParam(defaultValue = "10 Kyu") String danDegree,
                                         @RequestParam(defaultValue = "0") int worldRanking,
                                         @RequestParam(defaultValue = "A0") String bloodType) {

        String status = personService.create(personType, firstName, surname, address, birthday, email, gender, weight, danDegree, worldRanking, bloodType);
        if (!status.equals(SUCCES)) {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:The person was added.", HttpStatus.OK);
    }

    /**
     * Metoda pentru actualizarea unei persoane
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
     * @return mesaj corespunator
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<String> update(@RequestParam String personType,
                                         @RequestParam String firstName,
                                         @RequestParam String surname,
                                         @RequestParam String address,
                                         @RequestParam String birthday,
                                         @RequestParam String email,
                                         @RequestParam String gender,
                                         @RequestParam(defaultValue = "1.5") double weight,
                                         @RequestParam(defaultValue = "10 Kyu") String danDegree,
                                         @RequestParam(defaultValue = "0") int worldRanking,
                                         @RequestParam(defaultValue = "A0") String bloodType) {

        String status = personService.updatePerson(personType, firstName, surname, address, birthday, email, gender, weight, danDegree, worldRanking, bloodType);
        if (!status.equals(SUCCES)) {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:The person was updated.", HttpStatus.OK);
    }

    /**
     * Metoda pentru stergerea unei persoane dupa email
     *
     * @param email adresa de email
     * @return mesaj corespunzator
     */
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam String email) {
        String status = personService.deleteByEmail(email);
        if (!status.equals(SUCCES)) {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:The person was deleted.", HttpStatus.OK);
    }

    /**
     * Metoda pentru determinarea tuturor persoanelor din baza de date
     *
     * @return lista persoanelor
     */
    @GetMapping(value = "/getall")
    @ResponseBody
    public List<Person> getAll() {
        return personService.findAll();
    }
}
