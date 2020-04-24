package com.demo.service;

import com.demo.entity.Competition;
import com.demo.entity.category.Category;
import com.demo.repository.CompetitionRepository;
import com.demo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.demo.util.Constant.*;

@Service
public class CompetitionService {

    @Autowired
    CompetitionRepository competitionRepository;

    @Autowired
    CategoryService categoryService;

    public String create(String name,
                         String startDateStr,
                         String endDateStr,
                         String lastRegistrationDateStr,
                         String place,
                         String federation,
                         int noOfEntries,
                         int noOfCountries,
                         String competitionStatus){

        SimpleDateFormat dateformat = new SimpleDateFormat(DATE_FORMAT);
        Date startDate = null;
        Date endDate = null;
        Date lastRegistrationDate = null;
        try {
            startDate = dateformat.parse(startDateStr);
            endDate = dateformat.parse(endDateStr);
            lastRegistrationDate = dateformat.parse(lastRegistrationDateStr);
        } catch (ParseException e) {
            return ERR_DATE_FORMAT;
        }

        Competition newCompetition =  new Competition(0,name,startDate,endDate,lastRegistrationDate,place,federation,noOfEntries,noOfCountries,competitionStatus);
        String flag = Validator.checkCompetition(newCompetition);
        if(!flag.equals(SUCCES)) return flag;

        List<Competition> allCompetition = competitionRepository.findAll();
        for(Competition c : allCompetition){
            if(c.getName().equals(name)){
                return EXISTS + "competition " + newCompetition.getName();
            }
        }
        competitionRepository.save(newCompetition);
        return SUCCES;
    }

    public String updateCompetition(long competitionId,
                                    String name,
                                    String startDateStr,
                                    String endDateStr,
                                    String lastRegistrationDateStr,
                                    String place,
                                    String federation,
                                    int noOfEntries,
                                    int noOfCountries,
                                    String competitionStatus){

        Competition competition = competitionRepository.findById(competitionId).orElse(null);
        if (competition == null){
            return "Copmetition with id " + competitionId + " not found.";
        }

        SimpleDateFormat dateformat = new SimpleDateFormat(DATE_FORMAT);
        Date startDate = null;
        Date endDate = null;
        Date lastRegistrationDate = null;
        try {
            startDate = dateformat.parse(startDateStr);
            endDate = dateformat.parse(endDateStr);
            lastRegistrationDate = dateformat.parse(lastRegistrationDateStr);
        } catch (ParseException e) {
            return ERR_DATE_FORMAT;
        }

        Competition newCompetition =  new Competition(0,name,startDate,endDate,lastRegistrationDate,place,federation,noOfEntries,noOfCountries,competitionStatus);
        String flag = Validator.checkCompetition(newCompetition);
        if(!flag.equals(SUCCES)) return flag;

        newCompetition.setCompetitionId(competitionId);
        competitionRepository.save(newCompetition);
        return SUCCES;
    }

    public String addCategory(long competitionId, long categoryId){

        Category category = categoryService.findById(categoryId);
        if(category == null){
            return "[ERROR]:Category with id " + categoryId + " does not exist.";
        }

        Competition competition = competitionRepository.findById(competitionId).orElse(null);
        if(competition == null){
            return "[ERROR]:Competition with id " + competitionId + " does not exist.";
        }

        if(competition.getCategories().contains(category)){
            return "[WARNING]:" + category.getName() + " category is already assigned to" + competition.getName() + "competition.";
        }

        competition.addCategory(category);
        competitionRepository.save(competition);
        return SUCCES;
    }

    public String removeCategory(long competitionId, long categoryId){

        Category category = categoryService.findById(categoryId);
        if(category == null){
            return "[ERROR]:Category with id " + categoryId + " does not exist.";
        }

        Competition competition = competitionRepository.findById(competitionId).orElse(null);
        if(competition == null){
            return "[ERROR]:Competition with id " + competitionId + " does not exist.";
        }

        if(!competition.getCategories().contains(category)){
            return "[WARNING]:" + category.getName() + " category is not assigned to" + competition.getName() + "competition.";
        }

        competition.removeCategory(category);
        competitionRepository.save(competition);
        return SUCCES;
    }

    public String deleteById(long competitionId){

        Competition competition = competitionRepository.findById(competitionId).orElse(null);
        if (competition == null){
            return "Competition with id " + competitionId + " not found.";
        }
        competitionRepository.delete(competition);
        return SUCCES;
    }

    public List<Competition>findAll(){
        return competitionRepository.findAll();
    }

    public Competition findById(long competitionId){
        return competitionRepository.findById(competitionId).orElse(null);
    }
}
