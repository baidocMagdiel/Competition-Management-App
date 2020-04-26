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

/**
 * Clasa de service pentru Autentificare
 */
@Service
public class AutentificationService {

    @Autowired
    AutentificationRepository autentificationRepository;

    @AutoExternalize
    PersonService personService;

    /**
     * Metoda realizeaza inregistrarea unei persoane.
     *
     * @param personId     id-ul utilizatorului
     * @param password     parola
     * @param passwordConf reconfirmarea parolei
     * @return SUCCES sau mesaj de eroare
     */

    public String signUp(long personId, String password, String passwordConf) {
        if (!password.equals(passwordConf)) {
            return "[ERROR]:Passwords do not match.";
        }

        Person person = personService.findById(personId);
        if (person == null) {
            return "[ERROR]:Person with id " + personId + " not found.";
        }

        if (Validator.checkPassword(password) == false) {
            return "[ERROR]:Your password does not meet the required requirements.";
        }

        String encodedPass = new PasswordSecurity().hash(password);

        Autentification account = new Autentification(0, person, encodedPass);
        autentificationRepository.save(account);
        return SUCCES;
    }

    /**
     * Metoda schimba parola de autentificare a unui cont de utilizator
     *
     * @param email        adresa de email a utilizatorului
     * @param password     noua parola
     * @param passwordConf confirmarea parolei
     * @return SUCCES sau mesaj de eroare
     */
    public String changePassword(String email, String password, String passwordConf) {

        if (!password.equals(passwordConf)) {
            return "[ERROR]:Passwords do not match.";
        }

        Person person = personService.findByEmail(email);
        if (person == null) {
            return "[ERROR]:Person with email " + email + " not found.";
        }

        Autentification acount = autentificationRepository.findByPerson(person);
        if (acount == null) {
            return "[ERROR]:No account found for email address " + email;
        }

        if (Validator.checkPassword(password) == false) {
            return "[ERROR]:Your password does not meet the required requirements.";
        }

        String encodedPass = new PasswordSecurity().hash(password);

        Autentification newAccount = new Autentification(0, person, encodedPass);
        newAccount.setAutentificationId(acount.getAutentificationId());
        autentificationRepository.save(newAccount);
        return SUCCES;
    }

    /**
     * Metoda realizeaza autentificarea unui utilizator in cadrul aplicatiei.
     *
     * @param email    adresa de email
     * @param password parola
     * @return SUCCES sau mesaj de eroare
     */
    public String signIn(String email, String password) {

        Person person = personService.findByEmail(email);
        if (person == null) {
            return "[ERROR]:Person with email " + email + " not found.";
        }

        Autentification acount = autentificationRepository.findByPerson(person);
        if (acount == null) {
            return "[ERROR]:No account found for email address " + email;
        }

        boolean flag = (new PasswordSecurity()).authenticate(password, acount.getPassword());
        if (flag == false) {
            return "[ERROR]:Incorrect authentication data.";
        }
        return SUCCES;
    }

}
