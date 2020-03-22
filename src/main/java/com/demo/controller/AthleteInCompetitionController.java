package com.demo.controller;

import com.demo.entity.AthleteInCompetition;
import com.demo.repository.AthleteInCompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/athInComp")
public class AthleteInCompetitionController {
    private AthleteInCompetitionRepository athleteInCompetitionRepository;

    @Autowired
    public AthleteInCompetitionController() {
        this.athleteInCompetitionRepository = new AthleteInCompetitionRepository();
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public List<AthleteInCompetition> create(@RequestParam long competitionId,
                                             @RequestParam long categoryId,
                                             @RequestParam long athleteId,
                                             @RequestParam(defaultValue = "0") int place,
                                             @RequestParam(defaultValue = "0") int rankingPoints){

        AthleteInCompetition newTest = new AthleteInCompetition();
        newTest.setAthleteId(athleteId);
        newTest.setCategoryId(categoryId);
        newTest.setCompetitionId(competitionId);
        newTest.setPlace(place);
        newTest.setRankingPoints(rankingPoints);

        athleteInCompetitionRepository.insert(newTest);
        return athleteInCompetitionRepository.findAll("*");
    }

    @GetMapping(value="/deleteAll")
    public String deleteAll(){
        try{
            athleteInCompetitionRepository.deleteAll();
        } catch(Exception ex){
            return "[Error] The records were not deleted. " + ex.getMessage();
        }
        return "The records have been deleted.";
    }

    @GetMapping(value="/findAll")
    public List<AthleteInCompetition> findAll(){

        return athleteInCompetitionRepository.findAll("*");
    }
}
