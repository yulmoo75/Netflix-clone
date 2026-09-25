package com.netlion.netflix_clone.domain.mylist;

import com.netlion.netflix_clone.domain.content.Contents;
import com.netlion.netflix_clone.domain.content.ContentRepository;
import com.netlion.netflix_clone.domain.mylist.dto.MyListResponse;
import com.netlion.netflix_clone.domain.user.User;
import com.netlion.netflix_clone.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyListService {

    private final MyListRepository myListRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Transactional
    public void add(String email, Long contentId) {
        if (myListRepository.existsByUserEmailAndContentId(email, contentId)) {
            throw new IllegalArgumentException("이미 찜한 콘텐츠입니다.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Contents content = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 콘텐츠입니다."));

        myListRepository.save(MyList.builder().user(user).content(content).build());
    }

    @Transactional
    public void remove(String email, Long contentId) {
        MyList myList = myListRepository.findByUserEmailAndContentId(email, contentId)
                .orElseThrow(() -> new IllegalArgumentException("찜 목록에 없는 콘텐츠입니다."));
        myListRepository.delete(myList);
    }

    public List<MyListResponse> findMyList(String email) {
        return myListRepository.findByUserEmail(email).stream()
                .map(MyListResponse::new)
                .collect(Collectors.toList());
    }
}
