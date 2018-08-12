package com.puffin.articles.controller;

import com.puffin.articles.domain.Tag;
import com.puffin.articles.domain.TagResponseWrapper;
import com.puffin.articles.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Tag controller.
 * <p>
 * <p>Note that tags belong to Articles.
 * These end-points have been separated out into a separate controller for clarity.
 */
@RestController
@RequestMapping("/tags")
public class TagController {

	private static final Logger log = LoggerFactory.getLogger(TagController.class);

	ArticleService articleService;

	public TagController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@GetMapping("/{tagName}/{yearMonthDay}")
	public TagResponseWrapper getTag(@PathVariable(value = "tagName") String tagName, @PathVariable(value = "yearMonthDay") String yearMonthDay) {
		log.info("Request for tags recieved");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		LocalDate articleDate = LocalDate.parse(yearMonthDay, formatter);
		return articleService.getTagSummary(tagName, articleDate);

	}
}
