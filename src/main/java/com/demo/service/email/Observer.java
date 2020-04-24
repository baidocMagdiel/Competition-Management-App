package com.demo.service.email;

import com.demo.entity.Competition;
import com.demo.entity.person.Person;

public interface Observer {

    int update(Person person, Competition competition);
}
