package com.netlion.netflix_clone.domain.content;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "contents")
public class Contents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private String genre;

    private String director;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Contents(String title, String description, String genre, String director) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.director = director;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public void update(String title, String description, String genre, String director) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.director = director;
    }
}
