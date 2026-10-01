package com.netlion.netflix_clone.domain.history;

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
@Table(name = "histories", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "content_id"})
})
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private Contents content;

    @Column(name = "last_position", nullable = false)
    private int lastPosition;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public History(User user, Contents content, int lastPosition) {
        this.user = user;
        this.content = content;
        this.lastPosition = lastPosition;
        this.updatedAt = LocalDateTime.now();
    }

    public void updatePosition(int lastPosition) {
        this.lastPosition = lastPosition;
        this.updatedAt = LocalDateTime.now();
    }
}
