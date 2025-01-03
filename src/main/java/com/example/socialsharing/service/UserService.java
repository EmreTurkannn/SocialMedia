package com.example.socialsharing.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialsharing.model.Hobby;
import com.example.socialsharing.model.User;
import com.example.socialsharing.repository.HobbyRepository;
import com.example.socialsharing.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    // Tüm kullanıcıları listeleme
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Kullanıcıya id'ye göre erişim
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }


    // Kullanıcı güncelleme
    public void updateUser(User user) {
        userRepository.save(user);
    }

    // Kullanıcıyı silme
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Kullanıcı oluşturma ve hobileri ilişkilendirme
   public User createUserWithHobbies(User user, List<Long> hobbyIds) {
      // E-posta adresinin zaten mevcut olup olmadığını kontrol et
    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
        throw new RuntimeException("Bu e-posta adresi zaten kullanımda.");
    }

    Set<Hobby> hobbiesSet = new HashSet<>();
    for (Long hobbyId : hobbyIds) {
        Hobby hobby = hobbyRepository.findById(hobbyId)
                .orElseThrow(() -> new RuntimeException("Hobi bulunamadı"));
        hobbiesSet.add(hobby);
    }

    user.setHobbies(hobbiesSet);
    return userRepository.save(user); // Kaydedilen kullanıcıyı döndür
}

     public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
    }

    // Profil fotoğrafını güncelleme
    public void updateProfileImage(Long userId, String imageUrl) {
        User user = getUserById(userId);
        if (user != null) {
            user.setProfileImageUrl(imageUrl); // Fotoğraf URL'sini güncelle
            updateUser(user); // Kullanıcıyı veritabanında güncelle
        }
    }


}
