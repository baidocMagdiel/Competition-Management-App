import com.demo.entity.person.Coach;
import com.demo.entity.person.Person;
import com.demo.repository.PersonRepository;
import com.demo.service.PersonService;

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
import static org.mockito.Mockito.*;

public class PersonTest {

    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepository personRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void insertCorrectPersonTypeTest(){

        Person person = new Person();
        when(personRepository.save(any(Person.class))).thenReturn(person);
        String status = personService.create(COACH,"Ion","Ion","str.Batitiu 24 Cluj-Napoca","12/12/2020","ion.ion@gmail.com","Male",0,"",0,"");
        assertEquals(SUCCES,status);
    }

    @Test
    public void insertWrongPersonTypeTest(){

        Person person = new Person();
        when(personRepository.save(any(Person.class))).thenReturn(person);
        String status = personService.create("supererou","Ion","Ion","str.Batitiu 24 Cluj-Napoca","12/12/2020","ion.ion@gmail.com","Male",0,"",0,"");
        assertEquals("[ERROR]:Unknown/unsupported person-type [supererou]",status);
    }

    @Test
    public void insertBlackFieldTest(){

        Person person = new Person();
        when(personRepository.save(any(Person.class))).thenReturn(person);
        String status = personService.create(ATHLETE,"Ion","Ion","","12/12/2020","ion.ion@gmail.com","Male",0,"",0,"");
        assertEquals(EMPTY_FIELD,status);
    }

    @Test
    public void passedUpdateTest(){

        Person person = new Coach(0,"Ion","Ion","str.Baritiu 24 Cluj-Napoca","Male",new Date(),"ion.ion@gmail.com");
        when(personRepository.findByEmail(person.getEmail())).thenReturn(person);
        when(personRepository.save(any(Person.class))).thenReturn(person);
        String status = personService.updatePerson(COACH,"Ion","Ion","str.Baritiu 24 Cluj-Napoca","12/12/2020","ion.ion@gmail.com","Male",0,"",0,"");
        assertEquals(SUCCES,status);
    }

    @Test
    public void failedUpdateTest(){

        Person person = new Coach(0,"Ion","Ion","str.Baritiu 24 Cluj-Napoca","Male",new Date(),"ion.ion@gmail.com");
        when(personRepository.findByEmail(person.getEmail())).thenReturn(person);
        when(personRepository.save(any(Person.class))).thenReturn(person);
        String status = personService.updatePerson(ATHLETE,"Ion","Ion","str.Baritiu 24 Cluj-Napoca","12/12/2020","ion.ion@gmail.com","Male",0,"",0,"");
        assertEquals("[ERROR]:The person does not have the same type.",status);
    }

    @Test
    public void deleteByEmailTest(){

        Person person = new Coach(0,"Ion","Ion","str.Baritiu 24 Cluj-Napoca","Male",new Date(),"ion.ion@gmail.com");
        when(personRepository.findByEmail(person.getEmail())).thenReturn(person);
        when(personRepository.save(any(Person.class))).thenReturn(person);
        String status = personService.deleteByEmail("ion.ion@gmail.com");
        assertEquals(SUCCES,status);
    }

}
