package com.example.socialsharing.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialsharing.model.Comment;
import com.example.socialsharing.model.User;
import com.example.socialsharing.repository.CommentRepository;
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsForUser(User user) {
      List<Comment> comments = commentRepository.findByRecipient(user);
      return comments;
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

     // Tarih formatlama için yardımcı metot
    public String getFormattedTimestamp(LocalDateTime timestamp) {
        if (timestamp != null) {
            Date date = convertToDate(timestamp); // LocalDateTime'ı Date'e dönüştür
            return new SimpleDateFormat("dd MMM yyyy HH:mm").format(date); // Tarih formatla
        }
        return "Tarih bilgisi yok"; 
    }

    // LocalDateTime'ı Date'e dönüştürme
    private Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
