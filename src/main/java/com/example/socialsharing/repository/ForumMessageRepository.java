package com.example.socialsharing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.socialsharing.model.ForumMessage;

@Repository
public interface ForumMessageRepository extends JpaRepository<ForumMessage, Long> {
    Page<ForumMessage> findAllByOrderByTimestampDesc(Pageable pageable);
}
