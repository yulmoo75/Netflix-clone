package com.netlion.netflix_clone.domain.history.dto;

import com.netlion.netflix_clone.domain.history.History;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HistoryResponse {

    private Long contentId;
    private String title;
    private int lastPosition;
    private LocalDateTime updatedAt;

    public HistoryResponse(History history) {
        this.contentId = history.getContent().getId();
        this.title = history.getContent().getTitle();
        this.lastPosition = history.getLastPosition();
        this.updatedAt = history.getUpdatedAt();
    }
}
