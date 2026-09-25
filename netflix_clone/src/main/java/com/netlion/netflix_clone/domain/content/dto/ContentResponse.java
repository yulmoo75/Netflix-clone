package com.netlion.netflix_clone.domain.content.dto;

import com.netlion.netflix_clone.domain.content.Contents;
import com.netlion.netflix_clone.domain.genre.Genre;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Getter
public class ContentResponse {

    private Long id;
    private String title;
    private String description;
    private List<String> genres;
    private String director;
    private LocalDateTime createdAt;
    private String imageUrl;

    public ContentResponse(Contents content) {
        this.id = content.getId();
        this.title = content.getTitle();
        this.description = content.getDescription();
        this.director = content.getDirector();
        this.genres = content.getGenres().stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
        this.createdAt = content.getCreatedAt();
        this.imageUrl = content.getImageUrl();
    }
}
