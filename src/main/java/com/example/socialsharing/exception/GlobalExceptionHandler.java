package com.example.socialsharing.exception;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import jakarta.servlet.http.HttpSession;
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Genel exception handler
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
           System.out.println("Beklenmeyen bir hata oluştu: " + ex);
        model.addAttribute("errorMessage", "Beklenmeyen bir hata oluştu. Lütfen daha sonra tekrar deneyin.");
        return "error"; // Hata mesajını error.html'e yönlendir
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, Model model) {
        System.out.println("Çalışma zamanı hatası oluştu: " + ex);
        model.addAttribute("errorMessage", ex.getMessage()); // Hata mesajını model'e ekle
        return "error";  // Hata mesajını error.html'e yönlendir
    }
    @ExceptionHandler(MultipartException.class)
    public String handleMultipartException(MultipartException e, HttpSession session, Model model) {
    // Oturumdan kullanıcı kimliğini al
    Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
    
    if (loggedInUserId == null) {
        return "redirect:/signin";  // Kullanıcı giriş yapmadıysa, login sayfasına yönlendir
    }
    log.error("Error message: " + e.getMessage());
    String errorMessage = "Dosya boyutu çok büyük. Lütfen daha küçük bir dosya yükleyin.";
    String encodedErrorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
    return "redirect:/profile/" + loggedInUserId + "?errorMessage=" + encodedErrorMessage; // Hata sonrası kullanıcının profil sayfasına yönlendir
}
 
}
