package com.puffin.articles.repository;

import com.puffin.articles.domain.Article;
import com.puffin.articles.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	List<Article> findByTagsAndDate(Tag tag, LocalDate yearMonthDay);
}
