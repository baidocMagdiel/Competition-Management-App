package com.demo.controller;

import com.demo.entity.Participation;
import com.demo.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.util.Constant.SUCCES;

@RestController
@RequestMapping(value="/participation")
public class ParticipationController {

    @Autowired
    ParticipationService participationService;

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<String> create(@RequestParam long personId,
                                         @RequestParam long categoryId,
                                         @RequestParam long competitionId,
                                         @RequestParam int place,
                                         @RequestParam int rankingPoints){

        String status = participationService.addParticipation(personId,competitionId,categoryId,place,rankingPoints);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your participation was added.", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<String> update(@RequestParam long participationId,
                                         @RequestParam long personId,
                                         @RequestParam long categoryId,
                                         @RequestParam long competitionId,
                                         @RequestParam int place,
                                         @RequestParam int rankingPoints){

        String status = participationService.updateParticipation(participationId,personId,competitionId,categoryId,place,rankingPoints);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your participation was updated.", HttpStatus.OK);
    }

    @PostMapping(value = "/delelte")
    @ResponseBody
    public ResponseEntity<String> delelte(@RequestParam long participationId){

        String status = participationService.deleteParticipation(participationId);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your participation was deleted.", HttpStatus.OK);
    }

    @GetMapping(value = "/getall")
    @ResponseBody
    public List<Participation> getAll(){
        return participationService.findAll();
    }

}
