package com.puffin.articles.repository;

import com.puffin.articles.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {

	List<Article> findByTagsAndDate(String tagName, LocalDate yearMonthDay);
}
