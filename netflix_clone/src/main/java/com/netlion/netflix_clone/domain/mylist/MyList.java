package com.netlion.netflix_clone.domain.mylist;

import com.netlion.netflix_clone.domain.content.Contents;
import com.netlion.netflix_clone.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "my_lists", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "content_id"})
})
public class MyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private Contents content;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public MyList(User user, Contents content) {
        this.user = user;
        this.content = content;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
