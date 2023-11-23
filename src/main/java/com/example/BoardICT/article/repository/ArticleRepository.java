package com.example.BoardICT.article.repository;

import com.example.BoardICT.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByFixedDateDesc();
    List<Article> findAllByCategory(String category);
}