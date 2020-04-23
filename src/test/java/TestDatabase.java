import com.demo.entity.Club;
import com.demo.entity.Competition;
import com.demo.entity.person.Person;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ClubRepository;
import com.demo.repository.CompetitionRepository;
import com.demo.repository.PersonRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestDatabase {

    /**
     * Testarea operatiilor asupra tabelei Person
     */
    /*@Test
    public void personDbTest(){

        PersonRepository personRepository = new PersonRepository();
        Person newPerson = new Person();
        String personType = "Coach";

        newPerson.setDtype("");
        newPerson.setFirstName("Pop");
        newPerson.setSurname("Ion");
        newPerson.setAddress("Viilor 5 Sibiu");
        newPerson.setBirthday("12.12.1998");
        newPerson.setEmail("pop.ion@gmail.com");
        newPerson.setGender("Male");

        if(personType.equals("Coach")){
            newPerson.setClubId("AC231");
        }
        if(personType.equals("Athlete")){
            newPerson.setClubId("AC231");
            newPerson.setBloodType("A2");
            newPerson.setWeight(65);
            newPerson.setDanDegree("2DAN");
            newPerson.setWorldRanking(100);
        }

        personRepository.insert(newPerson);
        ArrayList<Person> rez = (ArrayList<Person>) personRepository.findAllById("personId",Long.toString(newPerson.getPersonId()));
        assert (rez != null);

        personRepository.deleteAll();
        rez = (ArrayList<Person>) personRepository.findAll("*");
        assert (rez == null);
    } */


    /**
     * Testarea operatiilor asupra tabelei Category
     */
   /* @Test
    public void categoryDbTest(){

        CategoryRepository categoryRepository = new CategoryRepository();
        Category newCategory = new Category();
        String categoryType = "Single";
        newCategory.setDtype(categoryType);
        newCategory.setName("Kata Junior");
        newCategory.setAgeRange("16-17");
        newCategory.setMatchTime(5);
        if(categoryType.equals("Single")){
            newCategory.setWeightRange("60-65");
        }
        if(categoryType.equals("Team")){
            newCategory.setNoOfTeamMembers(3);
            newCategory.setNoOfMatches(3);
        }
        categoryRepository.insert(newCategory);
        ArrayList<Category> rez = (ArrayList<Category>) categoryRepository.findAllById("categoryId",Long.toString(newCategory.getCategoryId()));
        assert (rez != null);

        categoryRepository.deleteAll();
        rez = (ArrayList<Category>) categoryRepository.findAll("*");
        assert (rez == null);
    } */

    /**
     * Testarea operatiilor asupra tabelei Competition
     */
   /* @Test
    public void competitionDbTest(){

        CompetitionRepository competitionRepository = new CompetitionRepository();
        Competition newCompetition = new Competition();

        newCompetition.setName("Campionat european");
        newCompetition.setDate("23.12.2020");
        newCompetition.setPlace("Cluj-Napoca Romania");
        newCompetition.setFederation("FRK");
        newCompetition.setNoOfCountries(0);
        newCompetition.setNoOfEntries(0);
        newCompetition.setCompetitionStatus("ACTIVE");

        competitionRepository.insert(newCompetition);
        ArrayList<Competition> rez = (ArrayList<Competition>) competitionRepository.findAllById("competitionId",Long.toString(newCompetition.getCompetitionId()));
        assert (rez != null);

        competitionRepository.deleteAll();
        rez = (ArrayList<Competition>) competitionRepository.findAll("*");

        assert (rez == null);
    } */

    /**
     * Testarea operatiilor asupra tabelei Club
     */
    /*@Test
    public void clubDbTest(){

        ClubRepository clubRepository = new ClubRepository();
        Club newClub = new Club();

        newClub.setAddress("Str. Magurii 88 Cluj-Napoca");
        newClub.setClubId("AC123");
        newClub.setName("ACS Tsubaki");

        clubRepository.insert(newClub);
        ArrayList<Club> rez = (ArrayList<Club>) clubRepository.findAllById("id",Long.toString(newClub.getId()));
        assert (rez != null);

        clubRepository.deleteAll();
        rez = (ArrayList<Club>) clubRepository.findAll("*");
        assert (rez == null);
    }  */
}
