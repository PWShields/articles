package com.puffin.articles.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
@Entity
public class Tag {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@NaturalId
	String name;

	@ManyToMany(fetch = FetchType.LAZY,

			mappedBy = "tags")
	private Set<Article> articles = new HashSet<>();

	public Tag() {
	}

	public Tag(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public Tag setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Tag setName(String name) {
		this.name = name;
		return this;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public Tag setArticles(Set<Article> articles) {
		this.articles = articles;
		return this;
	}
}
