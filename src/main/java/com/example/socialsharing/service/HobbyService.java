package com.example.socialsharing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialsharing.model.Hobby;
import com.example.socialsharing.repository.HobbyRepository;

@Service
public class HobbyService {

    @Autowired
    private HobbyRepository hobbyRepository;

    // Tüm hobileri veritabanından alma
    public List<Hobby> getAllHobbies() {
        return hobbyRepository.findAll();
    }

    // Yeni bir hobiyi kaydetme
    public Hobby saveHobby(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    // ID'ye göre hobi bulma
    public Hobby getHobbyById(Long id) {
        return hobbyRepository.findById(id).orElse(null);
    }
}
