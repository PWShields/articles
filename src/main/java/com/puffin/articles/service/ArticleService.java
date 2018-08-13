package com.puffin.articles.service;

import com.puffin.articles.domain.Article;
import com.puffin.articles.domain.ArticleCreateTO;
import com.puffin.articles.domain.TagResponseWrapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
	Article create(ArticleCreateTO article);

	List<Article> getArticles();

	Optional<Article> getOneArticle(Long id);

	TagResponseWrapper getTagSummary(String tagName, LocalDate articleDate);
}
