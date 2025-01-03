package com.example.socialsharing.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.socialsharing.model.ForumMessage;
import com.example.socialsharing.repository.ForumMessageRepository;

@Service
public class ForumMessageService {

    private final ForumMessageRepository forumMessageRepository;

    @Autowired
    public ForumMessageService(ForumMessageRepository forumMessageRepository) {
        this.forumMessageRepository = forumMessageRepository;
    }

    public ForumMessage addForumMessage(ForumMessage forumMessage) {
        if (forumMessage.getTimestamp() == null) {
            forumMessage.setTimestamp(LocalDateTime.now());
        }
        return forumMessageRepository.save(forumMessage);
    }

    public Page<ForumMessage> getMessagesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return forumMessageRepository.findAllByOrderByTimestampDesc(pageable);
    }
}