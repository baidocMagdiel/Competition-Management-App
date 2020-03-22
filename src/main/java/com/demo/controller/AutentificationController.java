package com.demo.controller;

import com.demo.entity.Autentification;
import com.demo.repository.AutentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/autentification")
public class AutentificationController {
    private AutentificationRepository autentificationRepository;

    @Autowired
    public AutentificationController() { this.autentificationRepository = new AutentificationRepository(); }

    /**
     * Insereaza un nou user(datele de autentificare)
     * @param email adresa de autentificare
     * @param password parola
     * @return lista cu toate inregistrarile din tabela
     */
    @PostMapping(value="/create")
    @ResponseBody
    public List<Autentification> create(@RequestParam String email,
                                        @RequestParam String password){

        Autentification newAutentification = new Autentification();

        newAutentification.setEmail(email);
        newAutentification.setPassword(password);

        autentificationRepository.insert(newAutentification);

        return autentificationRepository.findAll("*");
    }

    /**
     * Stergerea tuturor inregistrarilor din tabela
     * @return mesaj de eroare sau succes
     */
    @GetMapping(value="/deleteAll")
    public String deleteAll(){
        try{
            autentificationRepository.deleteAll();
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
    public List<Autentification> findAll(){

        return autentificationRepository.findAll("*");
    }
}
