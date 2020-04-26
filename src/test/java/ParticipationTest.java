import com.demo.entity.Competition;
import com.demo.entity.Participation;
import com.demo.entity.category.Category;
import com.demo.entity.category.SingleCategory;
import com.demo.entity.category.TeamCategory;
import com.demo.entity.person.Athlete;
import com.demo.entity.person.Person;
import com.demo.repository.ParticipationRepository;
import com.demo.service.CategoryService;
import com.demo.service.CompetitionService;
import com.demo.service.ParticipationService;
import com.demo.service.PersonService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.demo.util.Constant.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Clasa de test pentru participari la competiti
 */
public class ParticipationTest {

    @InjectMocks
    ParticipationService participationService;
    @Mock
    ParticipationRepository participationRepository;
    @Mock
    CompetitionService competitionService;
    @Mock
    CategoryService categoryService;
    @Mock
    PersonService personService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Testez adaugarea unei participari la competitie
     *
     * @throws ParseException exceptie de parsare
     */
    @Test
    public void addParticipationTest() throws ParseException {

        Date date = new Date();
        Competition competition = new Competition(6, "World Championship", date, date, date, "SIBIU", "FRK", 0, 0, ACTIVE);
        Category category1 = new TeamCategory(0, "KATA", "16-17", "Female", KATA, 5, 3, 1);
        Category category2 = new SingleCategory(10, "KATA", "16-17", "Female", KATA, 5, "");
        competition.getCategories().add(category1);
        competition.getCategories().add(category2);
        List<Long> personId = new ArrayList<>();
        personId.add((long) 12);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date date1 = simpleDateFormat.parse("12/12/2003");

        Participation participation = new Participation();
        Person athlete = new Athlete(12, "Ion", "Ion", "str.Baritiu 24 Cluj-Napoca", "Female", date1, "ion.ion@gmail.com", 78, "A2", "2KYU", 100);

        when(personService.findById(athlete.getPersonId())).thenReturn(athlete);
        when(competitionService.findById(competition.getCompetitionId())).thenReturn(competition);
        when(categoryService.findById(category2.getCategoryId())).thenReturn(category2);
        when(participationRepository.save(any(Participation.class))).thenReturn(participation);
        String status = participationService.addParticipation("", personId, competition.getCompetitionId(), category2.getCategoryId(), 0, 0);
        assertEquals(SUCCES, status);
    }
}
