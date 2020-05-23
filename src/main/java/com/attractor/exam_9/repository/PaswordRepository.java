package com.attractor.exam_9.repository;

import com.attractor.exam_9.model.Pasword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaswordRepository extends JpaRepository<Pasword, Integer> {

    boolean existsByToken(String topic);

    void deleteAll();

    Optional<Pasword> findByToken(String topic);
}
