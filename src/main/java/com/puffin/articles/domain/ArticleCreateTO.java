package com.puffin.articles.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class ArticleCreateTO {

	String title;

	LocalDate date;

	String body;

	private Set<String> tags = new HashSet<>();



}
