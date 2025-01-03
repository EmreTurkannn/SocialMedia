package com.example.socialsharing.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.socialsharing.model.Comment;
import com.example.socialsharing.model.User;
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByRecipient(User recipient);
}
