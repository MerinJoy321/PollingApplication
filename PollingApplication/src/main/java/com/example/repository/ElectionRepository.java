package com.example.repository;

import com.example.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {

    Optional<Election> findByTitle(String title);

    Optional<Election> findByActiveTrue();
    Optional<Election> findFirstByOrderByStartTimeDesc();

}
