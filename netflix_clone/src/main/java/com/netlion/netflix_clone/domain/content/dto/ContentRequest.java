package com.netlion.netflix_clone.domain.content.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ContentRequest {

    @NotBlank
    private String title;

    private String description;

    private String genre;

    private String director;
}
