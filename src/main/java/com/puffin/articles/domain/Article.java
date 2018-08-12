package com.puffin.articles.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String title;

	LocalDate date;

	String body;

	@JsonSerialize(using = CustomSetSerializer.class)
	@ManyToMany(fetch = FetchType.LAZY)
//			cascade = {
//					CascadeType.PERSIST,
//					CascadeType.MERGE
//			})
	@JoinTable(name = "article_tag",
			joinColumns = {@JoinColumn(name = "ARTICLE_ID")},
			inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
	private Set<Tag> tags = new HashSet<>();

	public Article(String title, LocalDate date, String body, Set<Tag> tags) {
		this.title = title;
		this.date = date;
		this.body = body;
		this.tags = tags;
	}

	public Article() {
	}

	//These support the builder pattern  as opposed to @Data which
	//would generate "normal" setters.

	public Long getId() {
		return id;
	}

	public Article setId(Long id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Article setTitle(String title) {
		this.title = title;
		return this;
	}

	public LocalDate getDate() {
		return date;
	}

	public Article setDate(LocalDate date) {
		this.date = date;
		return this;
	}

	public String getBody() {
		return body;
	}

	public Article setBody(String body) {
		this.body = body;
		return this;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public Article setTags(Set<Tag> tags) {
		this.tags = tags;
		return this;
	}
}
