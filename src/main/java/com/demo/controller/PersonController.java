package com.demo.controller;

import com.demo.entity.person.Person;
import com.demo.entity.person.PersonDto;
import com.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * Metoda pentru crearea unei persoane
     *
     * @param personDto persoana ce se doreste creata
     * @return persoana creata
     */
    @PostMapping(value = "/create")
    @ResponseBody
    public Person create(@RequestBody PersonDto personDto) {
        return personService.create(personDto);
    }


    /**
     * Metoda pentru actualizarea unei persoane
     *
     * @param personDto persoana ce se doreste actualizata
     * @return
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public Person update(@RequestBody PersonDto personDto) {
        return personService.updatePerson(personDto);
    }

    /**
     * Metoda pentru stergerea unei persoane dupa email
     *
     * @param email adresa de email
     * @return persoana stearsa
     */
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public Person delete(@RequestParam String email) {
        return personService.deleteByEmail(email);
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
