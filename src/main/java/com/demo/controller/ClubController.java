package com.demo.controller;

import com.demo.entity.Club;
import com.demo.repository.ClubRepository;
import com.demo.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.demo.util.Constant.SUCCES;


@RestController
@RequestMapping(value="/club")
public class ClubController {

    @Autowired
    ClubService clubService;

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<String> create(@RequestParam String clubId,
                                         @RequestParam String name,
                                         @RequestParam String address){


        String status = clubService.create(clubId, name, address);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your club was created.", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<String> update(@RequestParam long id,
                                         @RequestParam String clubId,
                                         @RequestParam String name,
                                         @RequestParam String address){


        String status = clubService.update(id, clubId, name, address);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your club was updated.", HttpStatus.OK);
    }
}
