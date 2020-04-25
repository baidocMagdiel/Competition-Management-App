package com.demo.controller;

<<<<<<< HEAD
import com.demo.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.demo.util.Constant.*;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> d08b3eea09340023bd4cca200e3311a4099cadb2

@RestController
@RequestMapping(value="/competition")
public class CompetitionController{
<<<<<<< HEAD

    @Autowired
    CompetitionService competitionService;

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<String> create(@RequestParam String name,
                                         @RequestParam String place,
                                         @RequestParam String federation,
                                         @RequestParam String startDate,
                                         @RequestParam String endDate,
                                         @RequestParam String lastRegistrationDate,
                                         @RequestParam(defaultValue = "0")int noOfEntries,
                                         @RequestParam(defaultValue = "0") int noOfCountries,
                                         @RequestParam(defaultValue = "ACTIVE") String competitionStatus){


        String status = competitionService.create(name,startDate,endDate,lastRegistrationDate,place,federation,noOfEntries,noOfCountries,competitionStatus);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your competition was added.", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<String> update(@RequestParam long competitionId,
                                         @RequestParam String name,
                                         @RequestParam String place,
                                         @RequestParam String federation,
                                         @RequestParam String startDate,
                                         @RequestParam String endDate,
                                         @RequestParam String lastRegistrationDate,
                                         @RequestParam(defaultValue = "0")int noOfEntries,
                                         @RequestParam(defaultValue = "0") int noOfCountries,
                                         @RequestParam(defaultValue = "ACTIVE") String competitionStatus){


        String status = competitionService.updateCompetition(competitionId,name,startDate,endDate,lastRegistrationDate,place,federation,noOfEntries,noOfCountries,competitionStatus);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your competition was updated.", HttpStatus.OK);
    }

    @PostMapping(value = "/addcategory")
    @ResponseBody
    public ResponseEntity<String> addCategory(@RequestParam long competitionId, @RequestParam long categoryId){

        String status = competitionService.addCategory(competitionId,categoryId);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your category was assigned to the competition.", HttpStatus.OK);
    }

    @PostMapping(value = "/removecategory")
    @ResponseBody
    public ResponseEntity<String> removeCategory(@RequestParam long competitionId, @RequestParam long categoryId){

        String status = competitionService.removeCategory(competitionId,categoryId);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:The category was removed to the competition.", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam long competitionId) {
        String status = competitionService.deleteById(competitionId);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your competition was deleted.", HttpStatus.OK);
    }
=======

>>>>>>> d08b3eea09340023bd4cca200e3311a4099cadb2
}
