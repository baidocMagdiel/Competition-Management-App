package com.example.sd2020.demo.controller;

import com.example.sd2020.demo.entity.Club;
import com.example.sd2020.demo.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/club")
public class ClubController {
    private ClubRepository clubRepository;

    @Autowired
    public ClubController(ClubRepository clubRepository) { this.clubRepository = clubRepository; }

    @PostMapping(value = "/create")
    @ResponseBody
    public List<Club> create(@RequestParam String clubId,
                             @RequestParam String name,
                             @RequestParam String address){

        Club newClub = new Club();

        newClub.setAddress(address);
        newClub.setClubId(clubId);
        newClub.setName(name);

        clubRepository.save(newClub);
        return clubRepository.findAll();
    }

    @GetMapping(value="/deleteAll")
    public String deleteAll(){
        try{
            clubRepository.deleteAll();
        } catch(Exception ex){
            return "[Error] The records were not deleted. " + ex.getMessage();
        }
        return "The records have been deleted.";
    }

    @GetMapping(value="/findAll")
    public List<Club> findAll(){

        return clubRepository.findAll();
    }
}
