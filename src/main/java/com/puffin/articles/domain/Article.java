package com.puffin.articles.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Article {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	String id;

	String title;

	LocalDate date;

	String body;

	@ElementCollection
	List<String> tags;

	//These support the builder pattern  as opposed to @Data which
	//would generate "normal" setters.

	public Article setTitle(String title) {
		this.title = title;
		return this;
	}

	public Article setDate(LocalDate date) {
		this.date = date;
		return this;
	}

	public Article setBody(String body) {
		this.body = body;
		return this;
	}

	public Article setTags(List<String> tags) {
		this.tags = tags;
		return this;
	}

}
