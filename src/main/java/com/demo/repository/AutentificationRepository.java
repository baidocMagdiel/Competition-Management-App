package com.demo.repository;

import com.demo.entity.Account;
import com.demo.entity.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutentificationRepository extends JpaRepository<Account,Long> {
    Account findByPerson(Person person);
}
