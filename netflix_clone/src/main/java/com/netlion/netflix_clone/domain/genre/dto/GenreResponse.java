package com.netlion.netflix_clone.domain.genre.dto;

import com.netlion.netflix_clone.domain.genre.Genre;
import lombok.Getter;

@Getter
public class GenreResponse {
    private Long id;
    private String name;

    public GenreResponse(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }
}
