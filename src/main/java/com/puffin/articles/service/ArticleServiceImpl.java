package com.puffin.articles.service;

import com.puffin.articles.domain.Article;
import com.puffin.articles.domain.ArticleCreateTO;
import com.puffin.articles.domain.Tag;
import com.puffin.articles.domain.TagResponseWrapper;
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

@Service
public class ArticleServiceImpl implements ArticleService {

	private static final Logger log = LoggerFactory.getLogger(ArticleService.class);

	ArticleRepository articleRepository;

	TagRepository tagRepository;

	public ArticleServiceImpl(ArticleRepository articleRepository, TagRepository tagRepository) {
		this.articleRepository = articleRepository;
		this.tagRepository = tagRepository;
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
		List<Article> articles = articleRepository.findByTagsAndDate(tagName, articleDate);
		return null;
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
			Tag tag = tagRepository.findByName(name);
			if (tag == null) {
				tag = new Tag(name);
				tagRepository.save(tag);
			}
			tags.add(tag);
		}
		return tags;
	}


}
