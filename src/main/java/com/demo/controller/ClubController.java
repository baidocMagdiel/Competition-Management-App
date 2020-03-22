package com.demo.controller;

import com.demo.entity.Club;
import com.demo.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/club")
public class ClubController {
    private ClubRepository clubRepository;

    @Autowired
    public ClubController() { this.clubRepository = new ClubRepository(); }

    /**
     * Inserarea unui nou club in bd
     * @param clubId id-ul clubului
     * @param name numele clubului
     * @param address adresa clubului
     * @return lista cu toate inregistrarile din tabela
     */
    @PostMapping(value = "/create")
    @ResponseBody
    public List<Club> create(@RequestParam String clubId,
                             @RequestParam String name,
                             @RequestParam String address){

        Club newClub = new Club();

        newClub.setAddress(address);
        newClub.setClubId(clubId);
        newClub.setName(name);

        clubRepository.insert(newClub);
        return clubRepository.findAll("*");
    }

    /**
     * Stergerea tuturor inregistrarilor din tabela
     * @return mesaj de eroare sau succes
     */
    @GetMapping(value="/deleteAll")
    public String deleteAll(){
        try{
            clubRepository.deleteAll();
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
    public List<Club> findAll(){

        return clubRepository.findAll("*");
    }
}
