package com.netlion.netflix_clone.domain.genre;

import com.netlion.netflix_clone.domain.genre.dto.GenreRequest;
import com.netlion.netflix_clone.domain.genre.dto.GenreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    @Transactional
    public Long create(GenreRequest request) {
        if (genreRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("이미 존재하는 장르입니다.");
        }
        Genre genre = Genre.builder().name(request.getName()).build();
        return genreRepository.save(genre).getId();
    }

    public List<GenreResponse> findAll() {
        return GenreRepository.findAll().stream()
                .map(GenreResponse::new)
                .collect(Collectors.toList());
    }
}
