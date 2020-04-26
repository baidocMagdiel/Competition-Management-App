package com.demo.controller;

import com.demo.service.AutentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.demo.util.Constant.SUCCES;

@RestController
@RequestMapping(value = "/autentification")
public class AutentificationController {

    @Autowired
    AutentificationService autentificationService;

    /**
     * Metoda pentru signUp
     *
     * @param personId     id-ul persoanei
     * @param password     parola
     * @param passwrodConf confiramarea parolei
     * @return mesaj corespunzator
     */
    @PostMapping(value = "/signup")
    @ResponseBody
    public ResponseEntity<String> signUp(@RequestParam long personId,
                                         @RequestParam String password,
                                         @RequestParam String passwrodConf) {

        String status = autentificationService.signUp(personId, password, passwrodConf);
        if (!status.equals(SUCCES)) {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your account was created.", HttpStatus.OK);
    }

    /**
     * Metoda pentru schimbarea parolei
     *
     * @param email        adresa de email a contului
     * @param password     noua parola
     * @param passwrodConf confirmare parola
     * @return mesaj corespunzator
     */
    @PostMapping(value = "/changepass")
    @ResponseBody
    public ResponseEntity<String> changePassword(@RequestParam String email,
                                                 @RequestParam String password,
                                                 @RequestParam String passwrodConf) {

        String status = autentificationService.changePassword(email, password, passwrodConf);
        if (!status.equals(SUCCES)) {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your password was created.", HttpStatus.OK);
    }

    /**
     * Metoda pentru autentificare
     *
     * @param email    adresa de email a contului
     * @param password parola
     * @return mesaj corespunzator
     */
    @PostMapping(value = "/signin")
    @ResponseBody
    public ResponseEntity<String> signIn(@RequestParam String email,
                                         @RequestParam String password) {

        String status = autentificationService.signIn(email, password);
        if (!status.equals(SUCCES)) {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Welcome!", HttpStatus.OK);
    }
}
