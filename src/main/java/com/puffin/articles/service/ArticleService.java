package com.puffin.articles.service;

import com.puffin.articles.domain.Article;
import com.puffin.articles.domain.Tag;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
	Article create(Article article);

	List<Article> getArticles();

	Optional<Article> getOneArticle(Long id);

	Tag getTagSummary(String tagName, LocalDate articleDate);
}
