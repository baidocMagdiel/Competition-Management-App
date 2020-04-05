package com.demo.service;

import com.demo.entity.Competition;
import com.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailSender implements Observer{

    @Autowired
    SpringTemplateEngine templateEngine;
    @Autowired
    JavaMailSender sender;

    /**
     * Trimite email personalizat catre un destinatar
     * @param person persoana careia se doreste trimiterea email-ului
     * @param competition noua competitie care a fost adaugata
     * @throws Exception
     */
    public void sendMail(Person person, Competition competition) throws Exception {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("surname",person.getSurname());
        model.put("firstname",person.getFirstName());
        model.put("name",competition.getName());
        model.put("place",competition.getPlace());
        model.put("date",competition.getDate());
        model.put("federation",competition.getFederation());

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("email-template", context);

        try {
            helper.setTo(person.getEmail());
            //helper.setTo("baidoc.magdiel@yahoo.com");
            helper.setText(html,true);
            helper.setSubject("New Competition Added");
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }

        sender.send(message);
    }

    /**
     * Metoda de update
     * @param person persoana careia se doreste trimiterea email-ului
     * @param competition noua competitie care a fost adaugata
     * @return -1 pentru esec si 0 pentru succes
     */
    @Override
    public int update(Person person, Competition competition) {
        try {
            sendMail(person,competition);
        } catch (Exception e) {

            e.printStackTrace();
            return -1;
        }
        return 0;
    }

}