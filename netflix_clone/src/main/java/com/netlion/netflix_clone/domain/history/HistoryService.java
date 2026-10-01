package com.netlion.netflix_clone.domain.history;

import com.netlion.netflix_clone.domain.content.Contents;
import com.netlion.netflix_clone.domain.content.ContentRepository;
import com.netlion.netflix_clone.domain.history.dto.HistoryResponse;
import com.netlion.netflix_clone.domain.user.User;
import com.netlion.netflix_clone.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Transactional
    public void saveProgress(String email, Long contentId, int lastPosition) {
        historyRepository.findByUserEmailAndContentId(email, contentId)
                .ifPresentOrElse(
                        history -> history.updatePosition(lastPosition),
                        () -> {
                            User user = userRepository.findByEmail(email)
                                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
                            Contents content = contentRepository.findById(contentId)
                                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 콘텐츠입니다."));
                            historyRepository.save(
                                    History.builder()
                                            .user(user)
                                            .content(content)
                                            .lastPosition(lastPosition)
                                            .build()
                            );
                        }
                );
    }

    public List<HistoryResponse> findMyHistory(String email) {
        return historyRepository.findByUserEmailOrderByUpdatedAtDesc(email).stream()
                .map(HistoryResponse::new)
                .collect(Collectors.toList());
    }

    public HistoryResponse findContinueWatching(String email, Long contentId) {
        return historyRepository.findByUserEmailAndContentId(email, contentId)
                .map(HistoryResponse::new)
                .orElse(null);
    }
}
