package com.netlion.netflix_clone.domain.content.dto;

import com.netlion.netflix_clone.domain.content.Contents;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ContentResponse {

    private Long id;
    private String title;
    private String description;
    private String genre;
    private String director;
    private LocalDateTime createdAt;

    public ContentResponse(Contents content) {
        this.id = content.getId();
        this.title = content.getTitle();
        this.description = content.getDescription();
        this.genre = content.getGenre();
        this.director = content.getDirector();
        this.createdAt = content.getCreatedAt();
    }
}
