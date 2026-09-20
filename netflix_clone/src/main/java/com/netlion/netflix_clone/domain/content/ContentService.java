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
import java.util.stream.Collectors;

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

        if (request.getGenreIds() != null && !request.getGenreIds().isEmpty()) {
            content.updateGenres(resolveGenres(request.getGenreIds()));
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

        if (request.getGenreIds() != null) {
            content.updateGenres(resolveGenres(request.getGenreIds()));
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!contentRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않는 콘텐츠입니다.");
        }
        contentRepository.deleteById(id);
    }

    private Set<Genre> resolveGenres(List<Long> genreIds) {
        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(genreIds));
        if (genres.size() != genreIds.size()) {
            throw new IllegalArgumentException("존재하지 않는 장르입니다.");
        }
        return genres;
    }
}
