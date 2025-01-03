package com.example.socialsharing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String imageUrl;  // Öğrencinin küçük resminin URL'si
    private String profileLink;  // Öğrencinin profil linki
    
    // Getter ve Setter metodları
}
