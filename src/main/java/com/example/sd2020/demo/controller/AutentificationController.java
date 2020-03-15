package com.example.sd2020.demo.controller;

import com.example.sd2020.demo.entity.Autentification;
import com.example.sd2020.demo.entity.Person;
import com.example.sd2020.demo.repository.AutentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/autentification")
public class AutentificationController {
    private AutentificationRepository autentificationRepository;

    @Autowired
    public AutentificationController(AutentificationRepository autentificationRepository) { this.autentificationRepository = autentificationRepository; }

    @PostMapping(value="/create")
    @ResponseBody
    public List<Autentification> create(@RequestParam String email,
                                        @RequestParam String password){

        Autentification newAutentification = new Autentification();

        newAutentification.setEmail(email);
        newAutentification.setPassword(password);

        autentificationRepository.save(newAutentification);

        return autentificationRepository.findAll();
    }

    @GetMapping(value="/deleteAll")
    public String deleteAll(){
        try{
            autentificationRepository.deleteAll();
        } catch(Exception ex){
            return "[Error] The records were not deleted. " + ex.getMessage();
        }
        return "The records have been deleted.";
    }

    @GetMapping(value="/findAll")
    public List<Autentification> findAll(){

        return autentificationRepository.findAll();
    }
}
