package com.puffin.articles.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TagAssembler {


	public TagResponseWrapper assemble(List<Article> articles, String tagName, Set<String> relatedTagNames) {

		List<Long> articleIds = articles.stream().map(
				article -> article.getId()
		).collect(Collectors.toList());


		return new TagResponseWrapper()
				.setArticles(articleIds)
				.setCount(articles.size())
				.setName(tagName)
				.setRelated_tags(relatedTagNames);

	}
}
