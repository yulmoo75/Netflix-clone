package com.netlion.netflix_clone.domain.content.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class ContentRequest {

    @NotBlank
    private String title;

    private String description;

    private String director;

    private List<Long> genreIds;
}
