package com.demo.service.email;

import com.demo.entity.Competition;
import com.demo.entity.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationCentre {

    //Observer
    @Autowired
    EmailSender emailSender = new EmailSender();
    
    /**
     * Notifica toti antrenorii de adaugarea unei noi competitii
     * @param newComCopmetition noua competitie ce este adaugata
     * @param personList lista de persoane ce trebuie notificate
     * @return -1 in caz de esec, 0 daca au fost notificati toti antrenorii
     */
    public int addNewCompetition(Competition newComCopmetition, List<Person> personList){

        for(Person p: personList){
            if( emailSender.update(p, newComCopmetition) == -1){
              return -1;
            }
        }
        return 0;
    }
}
