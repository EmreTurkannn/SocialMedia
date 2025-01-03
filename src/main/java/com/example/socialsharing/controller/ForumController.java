package com.example.socialsharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.socialsharing.model.ForumMessage;
import com.example.socialsharing.model.User;
import com.example.socialsharing.service.ForumMessageService;
import com.example.socialsharing.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ForumController {

    private final ForumMessageService forumMessageService;
    private final UserService userService;

    @Autowired
    public ForumController(ForumMessageService forumMessageService, UserService userService) {
        this.forumMessageService = forumMessageService;
        this.userService = userService;
    }

    @GetMapping("/forum")
    public String getForumMessages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Page<ForumMessage> forumMessagesPage = forumMessageService.getMessagesWithPagination(page, size);

        model.addAttribute("forumMessages", forumMessagesPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", forumMessagesPage.getTotalPages());

        return "forum";
    }

    @PostMapping("/forum")
    public String postForumMessage(@RequestParam String message, HttpSession session, Model model) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");

        if (loggedInUserId == null) {
            model.addAttribute("errorMessage", "Mesaj gönderebilmek için önce giriş yapmalısınız.");
            return "forum";
        }

        if (message == null || message.trim().isEmpty()) {
            model.addAttribute("errorMessage", "Mesaj boş olamaz.");
            return "forum";
        }

        ForumMessage forumMessage = new ForumMessage();
        forumMessage.setMessage(message);
        User user = userService.getUserById(loggedInUserId);
        forumMessage.setUser(user);

        forumMessageService.addForumMessage(forumMessage);

        return "redirect:/forum";
    }
}
