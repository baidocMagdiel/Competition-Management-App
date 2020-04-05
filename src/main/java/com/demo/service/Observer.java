package com.demo.service;

import com.demo.entity.Competition;
import com.demo.entity.Person;

public interface Observer {

    int update(Person person, Competition competition);
}
