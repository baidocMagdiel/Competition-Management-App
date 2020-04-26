import com.demo.entity.Competition;
import com.demo.entity.category.Category;
import com.demo.entity.category.TeamCategory;
import com.demo.repository.CompetitionRepository;
import com.demo.service.CategoryService;
import com.demo.service.CompetitionService;
import com.demo.service.PersonService;
import com.demo.service.email.NotificationCentre;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Date;

import static com.demo.util.Constant.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;


public class CompetitionTest {

    @InjectMocks
    CompetitionService competitionService;

    @Mock
    CompetitionRepository competitionRepository;

    @Mock
    NotificationCentre notificationCentre;

    @Mock
    PersonService personService;

    @Mock
    CategoryService categoryService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void insertCompetitionTest(){

        Competition competition = new Competition();
        when(personService.findAll()).thenReturn(null);
        when(competitionRepository.save(any(Competition.class))).thenReturn(competition);
        when(notificationCentre.addNewCompetition(any(Competition.class),anyList())).thenReturn(0);
        String status = competitionService.create("World Championship","12/12/2020","12/12/2020","12/12/2020","SIBIU","FRK",0,0,ACTIVE);
        assertEquals(SUCCES,status);
    }

    @Test
    public void updateCompetitionTest(){

        Date date = new Date();
        Competition competition = new Competition(6,"World Championship",date,date,date,"SIBIU","FRK",0,0,ACTIVE);
        when(competitionRepository.findById(competition.getCompetitionId())).thenReturn(java.util.Optional.of(competition));
        when(competitionRepository.save(any(Competition.class))).thenReturn(competition);
        String status = competitionService.updateCompetition(6,"World Championship","12/12/2020","12/12/2020","12/12/2020","SIBIU","FRK",0,0,ACTIVE);
        assertEquals(SUCCES,status);
    }

    @Test
    public void addCategoryTest(){

        Date date = new Date();
        Competition competition = new Competition(6,"World Championship",date,date,date,"SIBIU","FRK",0,0,ACTIVE);
        Category category = new TeamCategory(0,"KATA","16-17","Female",KATA,5,3,1);

        when(categoryService.findById(category.getCategoryId())).thenReturn(category);
        when(competitionRepository.save(any(Competition.class))).thenReturn(competition);
        when(competitionRepository.findById(competition.getCompetitionId())).thenReturn(java.util.Optional.of(competition));

        String status = competitionService.addCategory(competition.getCompetitionId(),category.getCategoryId());
        assertEquals(SUCCES,status);
    }

    @Test
    public void removeCategoryTest(){

        Date date = new Date();
        Competition competition = new Competition(6,"World Championship",date,date,date,"SIBIU","FRK",0,0,ACTIVE);
        Category category = new TeamCategory(0,"KATA","16-17","Female",KATA,5,3,1);
        competition.getCategories().add(category);

        when(categoryService.findById(category.getCategoryId())).thenReturn(category);
        when(competitionRepository.save(any(Competition.class))).thenReturn(competition);
        when(competitionRepository.findById(competition.getCompetitionId())).thenReturn(java.util.Optional.of(competition));

        String status = competitionService.removeCategory(competition.getCompetitionId(),category.getCategoryId());
        assertEquals(SUCCES,status);
    }

    @Test
    public void deleteByIdTest(){

        Date date = new Date();
        Competition competition = new Competition(0,"World Championship",date,date,date,"SIBIU","FRK",0,0,ACTIVE);
        when(competitionRepository.findById(competition.getCompetitionId())).thenReturn(java.util.Optional.of(competition));
        String status = competitionService.deleteById(0);
        assertEquals(SUCCES,status);
    }
}
