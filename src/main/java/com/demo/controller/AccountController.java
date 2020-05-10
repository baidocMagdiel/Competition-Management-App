package com.demo.controller;

import com.demo.entity.Account;
import com.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.demo.util.Constant.SUCCES;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    AccountService accountService;

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
    public Account signUp(@RequestParam long personId,
                          @RequestParam String password,
                          @RequestParam String passwrodConf) {

        return accountService.signUp(personId, password, passwrodConf);

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
    public Account changePassword(@RequestParam String email,
                                  @RequestParam String password,
                                  @RequestParam String passwrodConf) {

        return accountService.changePassword(email, password, passwrodConf);
    }

    /**
     * Metoda pentru autentificare
     *
     * @param email    adresa de email a contului
     * @param password parola
     * @return contul
     */
    @PostMapping(value = "/signin")
    @ResponseBody
    public Account signIn(@RequestParam String email,
                          @RequestParam String password) {

        return accountService.signIn(email, password);
    }
}
