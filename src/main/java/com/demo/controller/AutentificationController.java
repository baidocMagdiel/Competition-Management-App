package com.demo.controller;

import com.demo.service.AutentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.util.Constant.SUCCES;

@RestController
@RequestMapping(value = "/autentification")
public class AutentificationController {

    @Autowired
    AutentificationService autentificationService;

    @PostMapping(value = "/signup")
    @ResponseBody
    public ResponseEntity<String> signUp(@RequestParam long personId,
                                         @RequestParam String password,
                                         @RequestParam String passwrodConf){

        String status = autentificationService.signUp(personId, password, passwrodConf);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your account was created.", HttpStatus.OK);
    }

    @PostMapping(value = "/changepass")
    @ResponseBody
    public ResponseEntity<String> changePassword(@RequestParam String email,
                                                 @RequestParam String password,
                                                 @RequestParam String passwrodConf){

        String status = autentificationService.changePassword(email,password,passwrodConf);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your password was created.", HttpStatus.OK);
    }

    @PostMapping(value = "/signin")
    @ResponseBody
    public ResponseEntity<String> signIn(@RequestParam String email,
                                         @RequestParam String password){

        String status = autentificationService.signIn(email,password);
        if(!status.equals(SUCCES)){
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Welcome!", HttpStatus.OK);
    }
}
