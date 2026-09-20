package com.netlion.netflix_clone.domain.content;

import com.netlion.netflix_clone.domain.content.dto.ContentRequest;
import com.netlion.netflix_clone.domain.content.dto.ContentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody ContentRequest request) {
        return ResponseEntity.ok(contentService.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<ContentResponse>> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            Pageable pageable) {
        return ResponseEntity.ok(contentService.search(title, genre, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contentService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ContentRequest request) {
        contentService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
