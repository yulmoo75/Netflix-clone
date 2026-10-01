package com.netlion.netflix_clone.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {
    Optional<History> findByUserEmailAndContentId(String email, Long contentId);
    List<History> findByUserEmailOrderByUpdatedAtDesc(String email);
}
