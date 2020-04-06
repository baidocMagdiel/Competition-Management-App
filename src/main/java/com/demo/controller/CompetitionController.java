package com.demo.controller;

import com.demo.entity.Competition;
import com.demo.entity.Person;
import com.demo.repository.CompetitionRepository;
import com.demo.repository.PersonRepository;
import com.demo.service.NotificationCentre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/competition")
public class CompetitionController{
    private CompetitionRepository competitionRepository;
    private PersonRepository personRepository;

    @Autowired
    private NotificationCentre notificationCentre;

    @Autowired
    public CompetitionController() {
        this.competitionRepository = new CompetitionRepository();
        this.personRepository = new PersonRepository();
    }

    /**
     *Insereaza o noua competitie in tabela
     * @param name numele competitie
     * @param date  data
     * @param place locul de desfasurare
     * @param federation federatia
     * @param noOfEntries numarul de intrari in competitie
     * @param noOfCountries numarul de tari inscrise in competitie
     * @param competitionStatus statusul competitie
     * @return lista cu toate inregistrarile din tabela
     */
    @PostMapping(value="/create")
    @ResponseBody
    public List<Competition> create(@RequestParam String name,
                                    @RequestParam String date,
                                    @RequestParam String place,
                                    @RequestParam String federation,
                                    @RequestParam(defaultValue = "0")int noOfEntries,
                                    @RequestParam(defaultValue = "0") int noOfCountries,
                                    @RequestParam(defaultValue = "ACTIVE") String competitionStatus){

        Competition newCompetition = new Competition();

        newCompetition.setName(name);
        newCompetition.setDate(date);
        newCompetition.setPlace(place);
        newCompetition.setFederation(federation);
        newCompetition.setNoOfCountries(noOfCountries);
        newCompetition.setNoOfEntries(noOfEntries);
        newCompetition.setCompetitionStatus(competitionStatus);

        competitionRepository.insert(newCompetition);
        List<Person> personList = personRepository.findAll("*");
        notificationCentre.addNewCompetition(newCompetition, (ArrayList<Person>) personList);
        return competitionRepository.findAll("*");
    }

    /**
     * Stergerea tuturor inregistrarilor din tabela
     * @return mesaj de eroare sau succes
     */
    @GetMapping(value="/deleteAll")
    public String deleteAll(){
        try{
            competitionRepository.deleteAll();
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
    public List<Competition> findAll(){

        return competitionRepository.findAll("*");
    }

}
