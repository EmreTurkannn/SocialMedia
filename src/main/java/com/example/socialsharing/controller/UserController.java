package com.example.socialsharing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.socialsharing.model.Hobby;
import com.example.socialsharing.model.User;
import com.example.socialsharing.service.HobbyService;
import com.example.socialsharing.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HobbyService hobbyService;

    // Kayıt formunu görüntüleme
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        List<Hobby> hobbies = hobbyService.getAllHobbies();
        model.addAttribute("hobbies", hobbies);  
        return "register"; 
    }

    // Kullanıcı kaydetme
    @PostMapping("/register")
    public String registerUser(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String email,
                               @RequestParam String gender,
                               @RequestParam(required = false) List<Long> hobbies, 
                               @RequestParam String city,
                               @RequestParam(required = false) String bio,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               Model model,
                               HttpSession session) { 
        try {
            if (!password.equals(confirmPassword)) {
                model.addAttribute("errorMessage", "Şifreler uyuşmuyor.");
                return "register";
            }

            if (hobbies == null || hobbies.isEmpty()) {
                model.addAttribute("errorMessage", "En az bir hobi seçilmeli.");
                return "register"; 
            }

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setGender(gender);
            user.setCity(city);
            user.setBio(bio);
            user.setPassword(password);

            User savedUser = userService.createUserWithHobbies(user, hobbies);

            session.setAttribute("loggedInUserId", savedUser.getId());

            return "redirect:/profile/" + savedUser.getId();
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }

    // Giriş formunu görüntüleme
    @GetMapping("/signin")
    public String showSigninForm() {
        return "signin"; 
    }

    // Kullanıcı girişini kontrol etme
    @PostMapping("/signin")
    public String signinUser(@RequestParam String email,
                             @RequestParam String password,
                             HttpSession session,
                             Model model) {
        try {
            User user = userService.getUserByEmail(email);

            if (user != null && user.getPassword().equals(password)) {
                session.setAttribute("loggedInUserId", user.getId());
                return "redirect:/profile/" + user.getId(); 
            } else {
                model.addAttribute("errorMessage", "E-posta veya şifre hatalı.");
                return "signin";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Bir hata oluştu: " + e.getMessage());
            return "signin";
        }
    }

    // Kullanıcıları listeleme
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();  
        model.addAttribute("users", users);  
        return "users";  
    }

    // Çıkış işlemi
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/"; 
    }
}
