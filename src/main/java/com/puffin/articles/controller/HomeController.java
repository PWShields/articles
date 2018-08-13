package com.puffin.articles.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Home controller.
 *
 * <p>A courtesy class so user gets a valid response if they navigate to root.
 *
 */
@RestController
@RequestMapping("/")
public class HomeController {

	/**
	 * Index string.
	 *
	 * @return the welcome message
	 */
	@RequestMapping
	public String index() {
		return "Welcome to the Article API";
	}

}
