package com.example.Dosify.repository;

import com.example.Dosify.Enums.DoseNo;
import com.example.Dosify.model.Dose1;
import com.example.Dosify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByEmailId(String emailId);
}
