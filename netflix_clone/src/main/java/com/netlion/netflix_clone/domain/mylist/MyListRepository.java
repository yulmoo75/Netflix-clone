package com.netlion.netflix_clone.domain.mylist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyListRepository extends JpaRepository<MyList, Long> {
    List<MyList> findByUserEmail(String email);
    Optional<MyList> findByUserEmailAndContentId(String email, Long contentId);
    boolean existsByUserEmailAndContentId(String email, Long contentId);
}
