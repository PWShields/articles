package com.puffin.articles.controller;

import com.puffin.articles.domain.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Tag controller.
 * <p>
 * <p>Note that tags belong to Articles.
 * These end-points have been separated out into a separate controller for clarity.
 */
@RestController
@RequestMapping("/tags")
public class TagController {


	public ResponseEntity<Tag> getArticles() {

		return null;

	}
}
