package com.netlion.netflix_clone.domain.content;

import com.netlion.netflix_clone.domain.genre.Genre;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    private String director;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "content_genres",
            joinColums = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @Builder
    public Contents(String title, String description, String genre, String director) {
        this.title = title;
        this.description = description;
        this.director = director;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public void update(String title, String description, String genre, String director) {
        this.title = title;
        this.description = description;
        this.director = director;
    }

    public void updateGenres(Set<Genre> genres) {
        this.genres.clear();
        this.genres.addAll(genres);
    }
}
