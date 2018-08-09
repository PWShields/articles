package com.puffin.articles.service;

import com.puffin.articles.domain.Article;
import com.puffin.articles.domain.Tag;
import com.puffin.articles.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

	private static final Logger log = LoggerFactory.getLogger(ArticleService.class);

	ArticleRepository articleRepository;

	public ArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public Article create(Article article) {
		return articleRepository.save(article);
	}

	@Override
	public List<Article> getArticles() {
		return articleRepository.findAll();
	}

	@Override
	public Optional<Article> getOneArticle(String id) {
		return articleRepository.findById(id);
	}

	@Override
	public Tag getArticlesForTag(String tagName, LocalDate yearMonthDay) {
		List<Article> articles = articleRepository.findByTagsAndDate(tagName, yearMonthDay);
		return null;
	}
}
