package com.example.socialsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.socialsharing.model.Hobby;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
   
}
