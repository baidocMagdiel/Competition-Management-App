package com.demo.service;

import com.demo.entity.Competition;
import com.demo.entity.Participation;
import com.demo.entity.category.Category;
import com.demo.entity.person.Athlete;
import com.demo.entity.person.Person;
import com.demo.repository.ParticipationRepository;
import com.demo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.demo.util.Constant.*;

@Service
public class ParticipationService {

    @Autowired
    ParticipationRepository participationRepository;
    @Autowired
    PersonService personService;
    @Autowired
    CompetitionService competitionService;
    @Autowired
    CategoryService categoryService;

    public String addParticipation(long personId, long competitionId, long categoryId, int place, int rankingPoints){

        Person person = personService.findById(personId);
        Competition competition = competitionService.findById(competitionId);
        Category category = categoryService.findById(categoryId);

        if(person == null || category == null || competition == null){
            return EMPTY_FIELD;
        }

        if(!(person instanceof Athlete)){
            return "The person must be an athlete.";
        }

        if(!competition.getCategories().contains(category)){
            return "[ERROR]:" + category.getName() + " category is not assigned to" + competition.getName() + "competition.";
        }

        Participation newParticipation = new Participation(0,competition,category, (Athlete) person,place,rankingPoints);
        String flag = Validator.checkParticipation(newParticipation);
        if(!flag.equals(SUCCES)) return flag;

        participationRepository.save(newParticipation);
        return SUCCES;
    }

    public String updateParticipation(long participationId, long personId, long competitionId, long categoryId, int place, int rankingPoints){

        Participation participation = participationRepository.findById(participationId).orElse(null);
        if(participation == null ){
            return "Participation with id " + participationId + " not found.";
        }

        Person person = personService.findById(personId);
        Competition competition = competitionService.findById(competitionId);
        Category category = categoryService.findById(categoryId);

        if(person == null || category == null || competition == null){
            return EMPTY_FIELD;
        }

        if(!competition.getCategories().contains(category)){
            return "[ERROR]:" + category.getName() + " category is not assigned to" + competition.getName() + "competition.";
        }

        if(!(person instanceof Athlete)){
            return "The person must be an athlete.";
        }

        Participation newParticipation = new Participation(0,competition,category, (Athlete) person,place,rankingPoints);
        String flag = Validator.checkParticipation(newParticipation);
        if(!flag.equals(SUCCES)) return flag;

        newParticipation.setId(participationId);
        participationRepository.save(newParticipation);
        return SUCCES;
    }

    public String deleteParticipation(long participationId){

        Participation participation = participationRepository.findById(participationId).orElse(null);
        if (participation == null){
            return "Participation with id " + participationId + " not found.";
        }
        participationRepository.delete(participation);
        return SUCCES;
    }

    public List<Participation> findAll(){
        return participationRepository.findAll();
    }
}
