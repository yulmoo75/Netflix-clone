package com.netlion.netflix_clone.domain.content;

import com.netlion.netflix_clone.domain.content.dto.ContentRequest;
import com.netlion.netflix_clone.domain.content.dto.ContentResponse;
import com.netlion.netflix_clone.domain.genre.Genre;
import com.netlion.netflix_clone.domain.genre.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final GenreRepository genreRepository;

    @Transactional
    public Long create(ContentRequest request) {
        Contents content = Contents.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .director(request.getDirector())
                .build();

        if (request.getGenreNames() != null && !request.getGenreNames().isEmpty()) {
            content.updateGenres(resolveGenres(request.getGenreNames()));
        }

        return contentRepository.save(content).getId();
    }

    public Page<ContentResponse> search(String title, String genreName, Pageable pageable) {
        return contentRepository.search(title, genreName, pageable)
                .map(ContentResponse::new);
    }

    public ContentResponse findById(Long id) {
        Contents content = contentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 콘텐츠입니다."));
        return new ContentResponse(content);
    }

    @Transactional
    public void update(Long id, ContentRequest request) {
        Contents content = contentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 콘텐츠입니다."));

        content.update(request.getTitle(), request.getDescription(), request.getDirector());

        if (request.getGenreNames() != null) {
            content.updateGenres(resolveGenres(request.getGenreNames()));
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!contentRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않는 콘텐츠입니다.");
        }
        contentRepository.deleteById(id);
    }

    private Set<Genre> resolveGenres(List<String> genreNames) {
        Set<Genre> genres = new HashSet<>();
        for (String name : genreNames) {
            Genre genre = genreRepository.findByName(name)
                    .orElseGet(() -> genreRepository.save(Genre.builder().name(name).build()));
            genres.add(genre);
        }
        return genres;
    }
}