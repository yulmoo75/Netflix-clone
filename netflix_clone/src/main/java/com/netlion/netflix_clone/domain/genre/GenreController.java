package com.netlion.netflix_clone.domain.genre;

import com.netlion.netflix_clone.domain.genre.dto.GenreRequest;
import com.netlion.netflix_clone.domain.genre.dto.GenreResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody GenreRequest request) {
        return ResponseEntity.ok(genreService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<GenreResponse>> findAll() {
        return ResponseEntity.ok(genreService.findAll());
    }
}
