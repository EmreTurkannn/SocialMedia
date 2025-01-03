package com.example.socialsharing.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.socialsharing.model.Comment;
import com.example.socialsharing.model.Hobby;
import com.example.socialsharing.model.User;
import com.example.socialsharing.service.CommentService;
import com.example.socialsharing.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    // Profil sayfasını görüntüleme
   @GetMapping("/{userId}")
public String viewProfile(@PathVariable Long userId, Model model, HttpSession session) {
    Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");

    // Oturumdan loggedInUserId'yi kontrol et
    if (loggedInUserId != null) 
        model.addAttribute("loggedIn", true);  // Giriş yapmamış kullanıcıyı yönlendir
    else
        model.addAttribute("loggedIn", false);

    // Kullanıcıyı al
    User user = userService.getUserById(userId);
    if (user == null) {
        return "redirect:/";  // Kullanıcı bulunamadığında ana sayfaya yönlendir
    }

    // Kullanıcı bilgilerini model'e ekle
    model.addAttribute("user", user);
    model.addAttribute("fullName", user.getFullName());

    // Yorumları al
    List<Comment> comments = commentService.getCommentsForUser(user);

    // Her bir yorum için tarih formatlama işlemi
    for (Comment comment : comments) {
        String formattedTimestamp = commentService.getFormattedTimestamp(comment.getTimestamp());
        comment.setFormattedTimestamp(formattedTimestamp); 
    }

    model.addAttribute("comments", comments);
    // Hobileri al
    Set<Hobby> hobbiesSet = user.getHobbies();
    List<Hobby> hobbiesList = new ArrayList<>(hobbiesSet);
    model.addAttribute("hobbies", hobbiesList);

    // Kullanıcı kendi profilini mi görüntülüyor? 
    boolean isOwnProfile = user.getId().equals(loggedInUserId);
    model.addAttribute("isOwnProfile", isOwnProfile);

    return "profile"; // profile.html sayfası
}

    // Yorum ekleme işlemi
    @PostMapping("/{userId}/comment")
public String postComment(@PathVariable Long userId,
                          @RequestParam String commentText,
                          HttpSession session) {
    // Oturumdan loggedInUserId'yi al
    Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");

    // Kullanıcı giriş yapmadıysa, signin sayfasına yönlendir
    if (loggedInUserId == null) {
        return "redirect:/signin";
    }

    // Profil sahibi (yorum yapılacak kişi)
    User recipient = userService.getUserById(userId);

    // Yorum yapan kullanıcı (oturumdaki kullanıcı)
    User commenter = userService.getUserById(loggedInUserId);

    // Kendi profiline yorum yapılmasına izin verilmez
    if (commenter.getId().equals(recipient.getId())) {
        return "redirect:/profile/" + userId; 
    }

    // Yorum metninin boş olup olmadığını kontrol et
    if (commentText != null && !commentText.trim().isEmpty()) {
        Comment comment = new Comment();
        comment.setText(commentText);
        comment.setTimestamp(LocalDateTime.now());
        comment.setCommenter(commenter); // Yorum yapan kullanıcı
        comment.setRecipient(recipient); // Yorum yapılan kullanıcı
        
        // Yorum kaydet
        commentService.saveComment(comment);
    }

    // Yorum sonrası profili tekrar göster
    return "redirect:/profile/" + userId;
}

    // Profil fotoğrafı değiştirme işlemi
    @PostMapping("/change-photo")
public String changeProfilePhoto(@RequestParam("profilePhoto") MultipartFile profilePhoto,
                                 HttpSession session) throws IOException {
    Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");

    if (loggedInUserId == null) {
        return "redirect:/register";
    }

    // Fotoğraf kaydetme dizini
    String uploadDir = "src/main/resources/static/profilePhotos/"; 
    Files.createDirectories(Paths.get(uploadDir));

    // Unique dosya adı oluştur
    String uniqueFileName = UUID.randomUUID().toString() + "-" + profilePhoto.getOriginalFilename();

    // Fotoğrafı kaydet
    Path filePath = Paths.get(uploadDir + uniqueFileName);
    Files.write(filePath, profilePhoto.getBytes());

    // Veritabanında dosya adını kaydet
    userService.updateProfileImage(loggedInUserId, uniqueFileName);

    return "redirect:/profile/" + loggedInUserId;
}


}
