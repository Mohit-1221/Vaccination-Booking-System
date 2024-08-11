package com.example.Dosify.repository;

import com.example.Dosify.model.Dose2;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Dose2Repository extends JpaRepository<Dose2, Integer> {
}
