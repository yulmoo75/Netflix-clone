package com.netlion.netflix_clone.domain.content;

import com.netlion.netflix_clone.domain.content.dto.ContentRequest;
import com.netlion.netflix_clone.domain.content.dto.ContentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    @Transactional
    public Long create(ContentRequest request) {
        Contents content = Contents.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .genre(request.getGenre())
                .director(request.getDirector())
                .build();
        return contentRepository.save(content).getId();
    }

    public List<ContentResponse> findAll() {
        return contentRepository.findAll().stream()
                .map(ContentResponse::new)
                .collect(Collectors.toList());
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
        content.update(request.getTitle(), request.getDescription(), request.getGenre(), request.getDirector());
    }

    @Transactional
    public void delete(Long id) {
        if (!contentRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않는 콘텐츠입니다.");
        }
        contentRepository.deleteById(id);
    }
}
