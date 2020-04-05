package com.demo.controller;

import com.demo.entity.Competition;
import com.demo.entity.Person;
import com.demo.entity.SendEmail;
import com.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/person")
public class PersonController {
    private PersonRepository personRepository;
    private SendEmail sendEmail;

    @Autowired
    public PersonController() {
        this.personRepository = new PersonRepository();
        this.sendEmail = new SendEmail();
    }

    /**
     * Insereaza o noua persoana in baza date
     * @param personType tipul persoanei(administrator, sportiv, manager, antrenor)
     * @param firstName prenume
     * @param surname nume de familie
     * @param address adresa
     * @param birthday data de nastere
     * @param email adresa de email
     * @param gender sex
     * @param clubId id-ul clubului
     * @param weight greutate
     * @param danDegree grad
     * @param worldRanking loc in clasament
     * @param bloodType grupa sanguina
     * @return lista cu toate inregistrarile din tabela
     */
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
            newPerson.setWorldRanking(worldRanking);
        }

        personRepository.insert(newPerson);
        return personRepository.findAll("*");
    }

    /**
     * Stergerea tuturor inregistrarilor din tabela
     * @return mesaj de eroare sau succes
     */
    @GetMapping(value="/deleteAll")
    public String deleteAll(){
        try{
            personRepository.deleteAll();
        } catch(Exception ex){
            return "[Error] The records were not deleted. " + ex.getMessage();
        }
        return "The records have been deleted.";
    }

    /**
     * Afisarea tuturor inregistrarilor din tabela
     * @return mesaj de eroare sau succes
     */
    @GetMapping(value="/findAll")
    public List<Person> findAll(){

        return personRepository.findAll("*");
    }
}
