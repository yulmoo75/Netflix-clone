package com.netlion.netflix_clone.domain.mylist;

import com.netlion.netflix_clone.domain.mylist.dto.MyListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/my-lists")
@RequiredArgsConstructor
public class MyListController {

    private final MyListService myListService;

    @PostMapping("/{contentId}")
    public ResponseEntity<Void> add(@PathVariable Long contentId, Authentication authentication) {
        myListService.add(authentication.getName(), contentId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<Void> remove(@PathVariable Long contentId, Authentication authentication) {
        myListService.remove(authentication.getName(), contentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MyListResponse>> findMyList(Authentication authentication) {
        return ResponseEntity.ok(myListService.findMyList(authentication.getName()));
    }
}
