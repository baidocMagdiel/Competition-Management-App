package com.demo.service;

import com.demo.entity.Account;
import com.demo.entity.person.Person;
import com.demo.repository.AutentificationRepository;
import com.demo.util.PasswordSecurity;
import com.demo.util.Validator;
import com.demo.util.exception.AppRequestException;
import groovy.transform.AutoExternalize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.demo.util.Constant.EXISTS;
import static com.demo.util.Constant.SUCCES;

/**
 * Clasa de service pentru Autentificare
 */
@Service
public class AccountService {

    @Autowired
    AutentificationRepository autentificationRepository;

    @Autowired
    PersonService personService;

    /**
     * Metoda realizeaza inregistrarea unei persoane.
     *
     * @param personId     id-ul utilizatorului
     * @param password     parola
     * @param passwordConf reconfirmarea parolei
     * @return contul persoanei
     */

    public Account signUp(long personId, String password, String passwordConf) {
        if (!password.equals(passwordConf)) {
            throw new AppRequestException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        }

        Person person = personService.findById(personId);
        if (person == null) {
            throw new AppRequestException("\"Person with id \" + personId + \" not found.\"", HttpStatus.BAD_REQUEST);
        }

        if (Validator.checkPassword(password) == false) {
            throw new AppRequestException("Your password does not meet the required requirements.", HttpStatus.BAD_REQUEST);
        }

        String encodedPass = new PasswordSecurity().hash(password);

        Account account = new Account(0, person, encodedPass);
        autentificationRepository.save(account);
        return account;
    }

    /**
     * Metoda schimba parola de autentificare a unui cont de utilizator
     *
     * @param email        adresa de email a utilizatorului
     * @param password     noua parola
     * @param passwordConf confirmarea parolei
     * @return contul actualizat
     */
    public Account changePassword(String email, String password, String passwordConf) {

        if (!password.equals(passwordConf)) {

            throw new AppRequestException("Passwords do not match." + email, HttpStatus.BAD_REQUEST);
        }

        Person person = personService.findByEmail(email);
        if (person == null) {
            throw new AppRequestException("Person with email " + email + " not found." + email, HttpStatus.BAD_REQUEST);
        }

        Account acount = autentificationRepository.findByPerson(person);
        if (acount == null) {
            throw new AppRequestException("No account found for email address " + email, HttpStatus.BAD_REQUEST);
        }

        if (Validator.checkPassword(password) == false) {
            throw new AppRequestException("Your password does not meet the required requirements.", HttpStatus.BAD_REQUEST);
        }

        String encodedPass = new PasswordSecurity().hash(password);

        Account newAccount = new Account(0, person, encodedPass);
        newAccount.setAutentificationId(acount.getAutentificationId());
        autentificationRepository.save(newAccount);
        return newAccount;
    }

    /**
     * Metoda realizeaza autentificarea unui utilizator in cadrul aplicatiei.
     *
     * @param email    adresa de email
     * @param password parola
     * @return contul
     */
    public Account signIn(String email, String password) {

        Person person = personService.findByEmail(email);
        if (person == null) {
            throw new AppRequestException("Person with email " + email + " not found." + email, HttpStatus.BAD_REQUEST);
        }

        Account account = autentificationRepository.findByPerson(person);
        if (account == null) {
            throw new AppRequestException("No account found for email address " + email, HttpStatus.BAD_REQUEST);
        }

        boolean flag = (new PasswordSecurity()).authenticate(password, account.getPassword());
        if (flag == false) {
            throw new AppRequestException("Incorrect authentication data.", HttpStatus.BAD_REQUEST);
        }
        return account;
    }

}
