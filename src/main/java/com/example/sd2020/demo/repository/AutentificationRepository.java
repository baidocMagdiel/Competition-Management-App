package com.example.sd2020.demo.repository;

import com.example.sd2020.demo.entity.Autentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutentificationRepository extends JpaRepository<Autentification,Long> {
}
