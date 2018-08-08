package com.puffin.articles.controller;

import com.puffin.articles.domain.Article;
import com.puffin.articles.exception.ItemNotFoundException;
import com.puffin.articles.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type Article controller.
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

	private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

	ArticleService articleService;

	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	/**
	 * Gets articles.
	 * <p>
	 * <p>@todo Note we need to change this to support pagination</p>
	 *
	 * @return the articles
	 */
	@GetMapping()
	@ResponseBody
	public List<Article> getArticles() {
		return articleService.getArticles();
	}


	@GetMapping()
	@ResponseBody
	@RequestMapping("/{id}")
	public Article getOneArticle(@PathVariable(value = "id") String id) throws ItemNotFoundException {
		log.info("Request to get article {}", id);
		Optional<Article> article = articleService.getOneArticle(id);
		if (!article.isPresent()) {
			throw new ItemNotFoundException("Article NOT found, id: " + id);
		}
		return article.get();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createArticle(@RequestBody Article article) {
		log.info("Request to create article received");
		Article newArticle = articleService.create(article);
		log.info("Article {} created", newArticle.getId());
	}


}
