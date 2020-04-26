package com.demo.service;

import com.demo.entity.Competition;
import com.demo.entity.category.Category;
import com.demo.entity.person.Person;
import com.demo.repository.CompetitionRepository;
import com.demo.service.email.NotificationCentre;
import com.demo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.demo.util.Constant.*;

/**
 * Clasa de service pentru Competitie
 */
@Service
public class CompetitionService {

    @Autowired
    CompetitionRepository competitionRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    NotificationCentre notificationCentre;

    @Autowired
    PersonService personService;

    /**
     * Metoda pentru crearea unei competitii
     *
     * @param name                    numele competitiei
     * @param startDateStr            data de inceput
     * @param endDateStr              data de sfarsit
     * @param lastRegistrationDateStr ultima data de inregistrare
     * @param place                   locul de desfasurare
     * @param federation              federatia
     * @param noOfEntries             numarul de intrari
     * @param noOfCountries           numarul de tari/cluburi participante
     * @param competitionStatus       statusul competiei
     * @return SUCCES sau mesaj de eroare
     */
    public String create(String name,
                         String startDateStr,
                         String endDateStr,
                         String lastRegistrationDateStr,
                         String place,
                         String federation,
                         int noOfEntries,
                         int noOfCountries,
                         String competitionStatus) {

        SimpleDateFormat dateformat = new SimpleDateFormat(DATE_FORMAT);
        Date startDate = null;
        Date endDate = null;
        Date lastRegistrationDate = null;
        try {
            startDate = dateformat.parse(startDateStr);
            endDate = dateformat.parse(endDateStr);
            lastRegistrationDate = dateformat.parse(lastRegistrationDateStr);
        } catch (ParseException e) {
            return ERR_DATE_FORMAT;
        }

        Competition newCompetition = new Competition(0, name, startDate, endDate, lastRegistrationDate, place, federation, noOfEntries, noOfCountries, competitionStatus);
        String flag = Validator.checkCompetition(newCompetition);
        if (!flag.equals(SUCCES)) return flag;

        List<Competition> allCompetition = competitionRepository.findAll();
        for (Competition c : allCompetition) {
            if (c.getName().equals(name)) {
                return EXISTS + "competition " + newCompetition.getName();
            }
        }
        competitionRepository.save(newCompetition);

        //notificare
        List<Person> personList = personService.findAll();
        if (personList != null) {
            notificationCentre.addNewCompetition(newCompetition, personList);
        }
        return SUCCES;
    }

    /**
     * Metoda pentru actualizarea unei competitii
     *
     * @param competitionId           id-ul competitiei
     * @param name                    numele competitiei
     * @param startDateStr            data de inceput
     * @param endDateStr              data de sfarsit
     * @param lastRegistrationDateStr ultima data de inregistrare
     * @param place                   locul de desfasurare
     * @param federation              federatia
     * @param noOfEntries             numarul de intrari
     * @param noOfCountries           numarul de tari/cluburi participante
     * @param competitionStatus       statusul competiei
     * @return SUCCES sau mesaj de eroare
     */
    public String updateCompetition(long competitionId,
                                    String name,
                                    String startDateStr,
                                    String endDateStr,
                                    String lastRegistrationDateStr,
                                    String place,
                                    String federation,
                                    int noOfEntries,
                                    int noOfCountries,
                                    String competitionStatus) {

        Competition competition = competitionRepository.findById(competitionId).orElse(null);
        if (competition == null) {
            return "Copmetition with id " + competitionId + " not found.";
        }

        SimpleDateFormat dateformat = new SimpleDateFormat(DATE_FORMAT);
        Date startDate = null;
        Date endDate = null;
        Date lastRegistrationDate = null;
        try {
            startDate = dateformat.parse(startDateStr);
            endDate = dateformat.parse(endDateStr);
            lastRegistrationDate = dateformat.parse(lastRegistrationDateStr);
        } catch (ParseException e) {
            return ERR_DATE_FORMAT;
        }

        Competition newCompetition = new Competition(0, name, startDate, endDate, lastRegistrationDate, place, federation, noOfEntries, noOfCountries, competitionStatus);
        String flag = Validator.checkCompetition(newCompetition);
        if (!flag.equals(SUCCES)) return flag;

        newCompetition.setCompetitionId(competitionId);
        competitionRepository.save(newCompetition);
        return SUCCES;
    }

    /**
     * Metoda pentru asignarea unei categorii la o competitie
     *
     * @param competitionId id-ul competitiei
     * @param categoryId    id-ul categoriei
     * @return SUCCES sau mesaj de eroare
     */
    public String addCategory(long competitionId, long categoryId) {

        Category category = categoryService.findById(categoryId);
        if (category == null) {
            return "[ERROR]:Category with id " + categoryId + " does not exist.";
        }

        Competition competition = competitionRepository.findById(competitionId).orElse(null);
        if (competition == null) {
            return "[ERROR]:Competition with id " + competitionId + " does not exist.";
        }

        if (competition.getCategories().contains(category)) {
            return "[WARNING]:" + category.getName() + " category is already assigned to " + competition.getName() + "competition.";
        }

        competition.addCategory(category);
        competitionRepository.save(competition);
        return SUCCES;
    }

    /**
     * Metoda pentru stergerea unei categorii din competitie
     *
     * @param competitionId id-ul competitiei
     * @param categoryId    id-ul categoriei
     * @return SUCCES sau mesaj de eroare
     */
    public String removeCategory(long competitionId, long categoryId) {

        Category category = categoryService.findById(categoryId);
        if (category == null) {
            return "[ERROR]:Category with id " + categoryId + " does not exist.";
        }

        Competition competition = competitionRepository.findById(competitionId).orElse(null);
        if (competition == null) {
            return "[ERROR]:Competition with id " + competitionId + " does not exist.";
        }

        if (!competition.getCategories().contains(category)) {
            return "[WARNING]:" + category.getName() + " category is not assigned to " + competition.getName() + "competition.";
        }

        competition.removeCategory(category);
        competitionRepository.save(competition);
        return SUCCES;
    }

    /**
     * Metoda pentru stergerea unei competitii dupa id
     *
     * @param competitionId id-ul competitiei
     * @return SUCCES sau mesaj de eroare
     */
    public String deleteById(long competitionId) {

        Competition competition = competitionRepository.findById(competitionId).orElse(null);
        if (competition == null) {
            return "Competition with id " + competitionId + " not found.";
        }
        competitionRepository.delete(competition);
        return SUCCES;
    }

    /**
     * Metoda pentru returnarea tuturor competitiilor din baza de date
     *
     * @return lista competitiilor
     */
    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }

    /**
     * Metoda pentru cautarea unei competitii dupa id
     *
     * @param competitionId id-ul competitiei
     * @return competitia sau null
     */
    public Competition findById(long competitionId) {
        return competitionRepository.findById(competitionId).orElse(null);
    }
}
