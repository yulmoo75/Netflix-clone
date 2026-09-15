package com.netlion.netflix_clone.domain.genre.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class GenreRequest {
    @NotBlank
    private String name;
}
