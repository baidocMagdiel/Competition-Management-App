package com.demo.service;

import com.demo.entity.Club;
import com.demo.entity.person.Athlete;
import com.demo.entity.person.Person;
import com.demo.repository.ClubRepository;
import com.demo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.demo.util.Constant.SUCCES;

/**
 * Clasa de service pentu Club
 */
@Service
public class ClubService {

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    PersonService personService;

    /**
     * Metoda pentru crearea unui club
     *
     * @param clubId  numar inregistrare
     * @param name    numele clubului
     * @param address adresa
     * @return SUCCES sau mesaj de eroare
     */
    public String create(String clubId, String name, String address) {

        Club newClub = new Club(0, clubId, name, address);

        String flag = Validator.checkClub(newClub);
        if (!flag.equals(SUCCES)) return flag;

        clubRepository.save(newClub);
        return SUCCES;
    }

    /**
     * Metoda pentru actualizarea unui club
     *
     * @param id      id-ul clubului
     * @param clubId  numar inregistrare
     * @param name    numele clubului
     * @param address adresa
     * @return SUCCES sau mesaj de eroare
     */
    public String update(long id, String clubId, String name, String address) {

        Club club = clubRepository.findById(id).orElse(null);
        if (club == null) {
            return "Club with id " + id + " not found.";
        }

        Club newClub = new Club(0, clubId, name, address);

        String flag = Validator.checkClub(newClub);
        if (!flag.equals(SUCCES)) return flag;

        newClub.setId(id);
        clubRepository.save(newClub);
        return SUCCES;
    }

    /**
     * Metoda pentru adaugarea unui sportiv in club
     *
     * @param clubId    id-ul clubului
     * @param athleteId id-ul sportivului
     * @return SUCCES sau mesaj de eroare
     */
    public String addAthlete(long clubId, long athleteId) {

        Club club = clubRepository.findById(clubId).orElse(null);
        if (club == null) {
            return "[ERROR]:Club with id " + clubId + " not found.";
        }

        Person person = personService.findById(athleteId);
        if (person == null) {
            return "[ERROR]:Person with id " + athleteId + " not found.";
        }

        if (!(person instanceof Athlete)) {
            return "[ERROR]:The person must be an athlete.";
        }

        if (club.getAthletes().contains(person)) {
            return "[WARNING]:" + person.getFirstName() + " " + person.getSurname() + " athlete is already assigned to " + club.getName() + "club.";
        }

        club.addAthlete((Athlete) person);
        clubRepository.save(club);
        return SUCCES;
    }

    /**
     * Metoda pentru stergerea unei sportiv din club
     *
     * @param clubId    id-ul clubului
     * @param athleteId id-ul sportivului
     * @return SUCCES sau mesaj de eroare
     */
    public String removeAthlete(long clubId, long athleteId) {

        Club club = clubRepository.findById(clubId).orElse(null);
        if (club == null) {
            return "[ERROR]:Club with id " + clubId + " not found.";
        }

        Person person = personService.findById(athleteId);
        if (person == null) {
            return "[ERROR]:Person with id " + athleteId + " not found.";
        }

        if (!(person instanceof Athlete)) {
            return "[ERROR]:The person must be an athlete.";
        }

        if (club.getAthletes().contains(person)) {
            return "[WARNING]:" + person.getFirstName() + " " + person.getSurname() + " athlete is not assigned to " + club.getName() + "club.";
        }

        club.removeAthlete((Athlete) person);
        clubRepository.save(club);
        return SUCCES;
    }

    /**
     * Metoda pentru gasirea unei club dupa id
     *
     * @param clubId id-ul clubului
     * @return clubul sau null
     */
    public Club findById(long clubId) {
        return clubRepository.findById(clubId).orElse(null);
    }

}
