package com.puffin.articles.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@JsonPropertyOrder({ "tag", "count", "articles", "related_tags" })
public class TagResponseWrapper {

	@JsonProperty(value = "tag")
	String name;
	Integer count;
	List<Long> articles;
	Set<String> related_tags;

	public TagResponseWrapper setName(String name) {
		this.name = name;
		return this;
	}

	public TagResponseWrapper setCount(Integer count) {
		this.count = count;
		return this;
	}

	public TagResponseWrapper setArticles(List<Long> articles) {
		this.articles = articles;
		return this;
	}

	public TagResponseWrapper setRelated_tags(Set<String> related_tags) {
		this.related_tags = related_tags;
		return this;
	}
}
