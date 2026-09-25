package com.netlion.netflix_clone.domain.mylist.dto;

import com.netlion.netflix_clone.domain.mylist.MyList;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyListResponse {

    private Long id;
    private Long contentId;
    private String title;
    private LocalDateTime createdAt;

    public MyListResponse(MyList myList) {
        this.id = myList.getId();
        this.contentId = myList.getContent().getId();
        this.title = myList.getContent().getTitle();
        this.createdAt = myList.getCreatedAt();
    }
}
