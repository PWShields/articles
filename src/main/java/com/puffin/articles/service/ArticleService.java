package com.puffin.articles.service;

import com.puffin.articles.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
	Article create(Article article);

	List<Article> getArticles();

	Optional<Article> getOneArticle(String id);
}
