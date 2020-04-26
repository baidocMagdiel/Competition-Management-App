package com.demo.controller;

import com.demo.entity.Participation;
import com.demo.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.util.Constant.SUCCES;

@RestController
@RequestMapping(value = "/participation")
public class ParticipationController {

    @Autowired
    ParticipationService participationService;

    /**
     * Metoda pentru a inregistra o participare la o competitie
     *
     * @param name          numele echipei
     * @param personId      lista cu id-urile sportivilor
     * @param competitionId id-ul competitiei
     * @param categoryId    id-ul categoriei
     * @param place         loc in clasament
     * @param rankingPoints puncte acumulate
     * @return mesaj corespunzator
     */
    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<String> create(@RequestParam(defaultValue = "") String name,
                                         @RequestBody List<Long> personId,
                                         @RequestParam long categoryId,
                                         @RequestParam long competitionId,
                                         @RequestParam int place,
                                         @RequestParam int rankingPoints) {

        String status = participationService.addParticipation(name, personId, competitionId, categoryId, place, rankingPoints);
        if (!status.equals(SUCCES)) {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your participation was added.", HttpStatus.OK);
    }

    /**
     * Metoda pentru a actualizarea unei participari
     *
     * @param participationId id-ul participarii
     * @param name            numele echipei
     * @param personId        lista cu id-urile sportivilor
     * @param competitionId   id-ul competitiei
     * @param categoryId      id-ul categoriei
     * @param place           loc in clasament
     * @param rankingPoints   puncte acumulate
     * @return mesaj corespunzator
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<String> update(@RequestParam long participationId,
                                         @RequestParam(defaultValue = "") String name,
                                         @RequestBody List<Long> personId,
                                         @RequestParam long categoryId,
                                         @RequestParam long competitionId,
                                         @RequestParam int place,
                                         @RequestParam int rankingPoints) {

        String status = participationService.updateParticipation(participationId, name, personId, competitionId, categoryId, place, rankingPoints);
        if (!status.equals(SUCCES)) {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your participation was updated.", HttpStatus.OK);
    }

    /**
     * Stergerea unei participari
     *
     * @param participationId id-ul participarii
     * @return mesaj corespunzator
     */
    @PostMapping(value = "/delelte")
    @ResponseBody
    public ResponseEntity<String> delelte(@RequestParam long participationId) {

        String status = participationService.deleteParticipation(participationId);
        if (!status.equals(SUCCES)) {
            return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("[INFO]:Your participation was deleted.", HttpStatus.OK);
    }

    /**
     * Metoda pentru determinarea tuturor participarile la competitii
     *
     * @return lista cu participarile
     */
    @GetMapping(value = "/getall")
    @ResponseBody
    public List<Participation> getAll() {
        return participationService.findAll();
    }

}
