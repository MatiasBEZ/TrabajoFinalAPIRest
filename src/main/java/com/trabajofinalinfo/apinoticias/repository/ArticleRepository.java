package com.trabajofinalinfo.apinoticias.repository;

import com.trabajofinalinfo.apinoticias.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(
            value = "SELECT * FROM article INNER JOIN author ON article.author_id = author.id " +
                    "WHERE article.title LIKE %:filter% " +
                    "OR article.description LIKE %:filter% OR article.content " +
                    "LIKE %:filter% OR author.fullname LIKE %:filter% AND article.published = 1",
            countQuery = "SELECT count(*) FROM article",
            nativeQuery = true)
    Page<Article> findByFilter(@Param("filter") String filter, Pageable pageable);
}
