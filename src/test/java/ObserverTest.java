import com.demo.entity.Competition;
import com.demo.entity.person.Coach;
import com.demo.entity.person.Person;
import com.demo.service.email.EmailSender;
import com.demo.service.email.NotificationCentre;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.demo.util.Constant.ACTIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ObserverTest {

    @InjectMocks
    private NotificationCentre notificationCentre;

    @Mock
    EmailSender emailSender;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Testez observerul
     */
    @Test
    public void testObserver() {


        Person person1 = new Coach(0,"Ion","Ion","str.Baritiu 24 Cluj-Napoca","Male",new Date(),"ion.ion@gmail.com");
        Person person2 = new Coach(1,"Popescu","Alex","str.Targului 12 Sibiu","Male",new Date(),"poescu.alex@gmail.com");
        Person person3 = new Coach(2,"Toma","Gheorhge","str.Dambovitei 20 Cluj-Napoca","Male",new Date(),"toma.gheorghe@gmail.com");

        Date date = new Date();
        Competition newCompetition = new Competition(6,"World Championship",date,date,date,"SIBIU","FRK",0,0,ACTIVE);

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);

        when(emailSender.update(any(Person.class), any(Competition.class))).thenReturn(0);

        int status = notificationCentre.addNewCompetition(newCompetition,personList);
        assertEquals(0,status);
    }

}
