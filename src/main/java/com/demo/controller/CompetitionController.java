package com.demo.controller;

import com.demo.entity.Competition;
import com.demo.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/competition")
public class CompetitionController {
    private CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionController() { this.competitionRepository = new CompetitionRepository(); }

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
        return competitionRepository.findAll("*");
    }

    @GetMapping(value="/deleteAll")
    public String deleteAll(){
        try{
            competitionRepository.deleteAll();
        } catch(Exception ex){
            return "[Error] The records were not deleted. " + ex.getMessage();
        }
        return "The records have been deleted.";
    }

    @GetMapping(value="/findAll")
    public List<Competition> findAll(){

        return competitionRepository.findAll("*");
    }
}
