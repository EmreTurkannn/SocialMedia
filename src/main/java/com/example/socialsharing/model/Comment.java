package com.example.socialsharing.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private LocalDateTime timestamp;

    @ManyToOne
    private User commenter;

    @ManyToOne
    private User recipient;

    // Formatlanmış tarih alanı
    private String formattedTimestamp;

    // Getter ve setter metotları
    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public User getCommenter() {
        return commenter;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getFormattedTimestamp() {
        return formattedTimestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setFormattedTimestamp(String formattedTimestamp) {
        this.formattedTimestamp = formattedTimestamp;
    }
}
