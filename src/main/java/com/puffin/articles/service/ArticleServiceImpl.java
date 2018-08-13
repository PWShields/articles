package com.puffin.articles.service;

import com.puffin.articles.domain.*;
import com.puffin.articles.repository.ArticleRepository;
import com.puffin.articles.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

	private static final Logger log = LoggerFactory.getLogger(ArticleService.class);

	ArticleRepository articleRepository;

	TagRepository tagRepository;

	TagAssembler tagAssembler;

	public ArticleServiceImpl(ArticleRepository articleRepository, TagRepository tagRepository, TagAssembler tagAssembler) {
		this.articleRepository = articleRepository;
		this.tagRepository = tagRepository;
		this.tagAssembler = tagAssembler;
	}

	@Override
	public Article create(ArticleCreateTO articleTO) {
		Article article = assembleArticleFromTransferObject(articleTO);
		return articleRepository.save(article);
	}

	@Override
	public List<Article> getArticles() {
		return articleRepository.findAll();
	}

	@Override
	public Optional<Article> getOneArticle(Long id) {
		return articleRepository.findById(id);
	}

	@Override
	public TagResponseWrapper getTagSummary(String tagName, LocalDate articleDate) {
		Tag tag = convertTagNameToTag(tagName);
		List<Article> articles = articleRepository.findByTagsAndDate(tag, articleDate);
		Set<String> relatedTagNames = buildRelatedTagNames(articles, tagName);
		TagResponseWrapper response = tagAssembler.assemble(articles, tagName, relatedTagNames);
		return response;
	}


	private Article assembleArticleFromTransferObject(ArticleCreateTO articleCreateTO) {
		Set<Tag> tags = convertTagNamesToTags(articleCreateTO.getTags());

		return new Article()
				.setBody(articleCreateTO.getBody())
				.setDate(articleCreateTO.getDate())
				.setTitle(articleCreateTO.getTitle())
				.setTags(tags);
	}

	private Set<Tag> convertTagNamesToTags(Set<String> tagNames) {
		Set<Tag> tags = new HashSet<>();
		for (String name : tagNames) {
			tags.add(convertTagNameToTag(name));
		}
		return tags;
	}

	private Tag convertTagNameToTag(String tagName) {
		Tag tag = tagRepository.findByName(tagName);
		if (tag == null) {
			tag = new Tag(tagName);
			tagRepository.save(tag);
		}
		return tag;
	}

	private Set<String> buildRelatedTagNames(List<Article> articles, String queryTag) {
		Set<Tag> tags = buildTags(articles);
		Set<String> tagNames = tags.stream().map(
				tag -> tag.getName()
		).collect(Collectors.toSet());
		return removeQueryTag(tagNames, queryTag);
	}

	private Set<Tag> buildTags(List<Article> articles) {
		Set<Tag> allTags = new HashSet<>();
		for (Article article : articles) {
			allTags.addAll(article.getTags());
		}
		return allTags;
	}

	private Set<String> removeQueryTag(Set<String> tagNames, String queryTag) {
		tagNames.remove(queryTag);
		return tagNames;
	}

}
