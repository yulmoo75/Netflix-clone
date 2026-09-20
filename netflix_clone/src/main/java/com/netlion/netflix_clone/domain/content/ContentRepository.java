package com.netlion.netflix_clone.domain.content;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContentRepository extends JpaRepository<Contents, Long> {
    @Query("""
        SELECT DISTINCT c FROM Contents c
        LEFT JOIN c.genres g
        WHERE (:title IS NULL OR c.title LIKE %:title%)
        AND (:genreName IS NULL OR g.name = :genreName)
        """)
    Page<Contents>search(@Param("title") String title,
                         @Param("genreName") String genreName,
                         Pageable pagable);
}
