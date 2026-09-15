package com.netlion.netflix_clone.domain.genre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    boolean existsByName(String name);
}
