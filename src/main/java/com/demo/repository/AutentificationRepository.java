package com.demo.repository;

import com.demo.entity.Autentification;
import com.demo.entity.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutentificationRepository extends JpaRepository<Autentification,Long> {
    Autentification findByPerson(Person person);
}
