package com.netlion.netflix_clone.domain.history;

import com.netlion.netflix_clone.domain.history.dto.HistoryRequest;
import com.netlion.netflix_clone.domain.history.dto.HistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/histories")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @PostMapping("/{contentId}")
    public ResponseEntity<Void> saveProgress(
            @PathVariable Long contentId,
            @RequestBody HistoryRequest request,
            Authentication authentication) {
        historyService.saveProgress(authentication.getName(), contentId, request.getLastPosition());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<HistoryResponse>> findMyHistory(Authentication authentication) {
        return ResponseEntity.ok(historyService.findMyHistory(authentication.getName()));
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<HistoryResponse> findContinueWatching(
            @PathVariable Long contentId,
            Authentication authentication) {
        return ResponseEntity.ok(historyService.findContinueWatching(authentication.getName(), contentId));
    }
}
