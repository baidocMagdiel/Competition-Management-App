package com.demo;

import com.demo.entity.Competition;
import com.demo.entity.Person;
import com.demo.service.CompetitionService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.testng.annotations.BeforeMethod;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.util.ArrayList;

public class TestObserver {

    @InjectMocks
    private CompetitionService competitionService = new CompetitionService();

    @Mock
    SpringTemplateEngine templateEngine;
    @Mock
    JavaMailSender sender;

    @BeforeMethod
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testObserver() {


        Person person1 = new Person("Coach", 1,"Florin-Magdiel","Baidoc", "Viilor 5 Sibiu","Male","14.06.1998","baidoc.magdiel@yahoo.com","AC123", 0,null, "2DAN",0);
        Person person2 = new Person("Coach", 1,"Lavinia Simona","Baidoc", "Magurii 81 Sibiu","Female","21.03.2000","laviniabaidoc21@gmail.com","AB122", 0,null, "1DAN",0);
        Person person3 = new Person("Coach", 1,"Stefania Maria","Finica", "Strada Noua 120 Sura Mare","Female","21.08.1998","stefaniafinica@gmail.com","FG133", 0,null, "1DAN",0);

        Competition newCompetition = new Competition(0,"TOKYO 2020 - QUALIFICATION TOURNAMENT","23.07.2020 - 26.07.2020","PARIS","WKF",0,0,"Active");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);

        int status = competitionService.addNewCompetition(newCompetition,personList);
        assert(status == 0);
    }

}
