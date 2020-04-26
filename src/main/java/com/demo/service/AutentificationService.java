package com.demo.service;

import com.demo.entity.Autentification;
import com.demo.entity.person.Person;
import com.demo.repository.AutentificationRepository;
import com.demo.util.PasswordSecurity;
import com.demo.util.Validator;
import groovy.transform.AutoExternalize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.demo.util.Constant.SUCCES;

@Service
public class AutentificationService {

    @Autowired
    AutentificationRepository autentificationRepository;

    @AutoExternalize
    PersonService personService;

    public String signUp(long personId, String password, String passwordConf){
        if(!password.equals(passwordConf)){
            return "[ERROR]:Passwords do not match.";
        }

        Person person = personService.findById(personId);
        if(person == null){
            return "[ERROR]:Person with id " + personId + " not found.";
        }

        if(Validator.checkPassword(password) == false){
            return "[ERROR]:Your password does not meet the required requirements.";
        }

        String encodedPass = new PasswordSecurity().hash(password);

        Autentification account = new Autentification(0,person,encodedPass);
        autentificationRepository.save(account);
        return SUCCES;
    }

    public String changePassword(String email, String password, String passwordConf){

        if(!password.equals(passwordConf)){
            return "[ERROR]:Passwords do not match.";
        }

        Person person = personService.findByEmail(email);
        if(person == null){
            return "[ERROR]:Person with email " + email + " not found.";
        }

        Autentification acount = autentificationRepository.findByPerson(person);
        if(acount == null){
            return "[ERROR]:No account found for email address " + email;
        }

        if(Validator.checkPassword(password) == false){
            return "[ERROR]:Your password does not meet the required requirements.";
        }

        String encodedPass = new PasswordSecurity().hash(password);

        Autentification newAccount = new Autentification(0,person,encodedPass);
        newAccount.setAutentificationId(acount.getAutentificationId());
        autentificationRepository.save(newAccount);
        return SUCCES;
    }

    public String signIn(String email, String password){

        Person person = personService.findByEmail(email);
        if(person == null){
            return "[ERROR]:Person with email " + email + " not found.";
        }

        Autentification acount = autentificationRepository.findByPerson(person);
        if(acount == null){
            return "[ERROR]:No account found for email address " + email;
        }

        boolean flag =  (new PasswordSecurity()).authenticate(password,acount.getPassword());
        if(flag == false){
            return "[ERROR]:Incorrect authentication data.";
        }
        return SUCCES;
    }

}
