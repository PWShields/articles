package com.puffin.articles.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puffin.articles.ArticlesApplication;
import com.puffin.articles.domain.ArticleCreateTO;
import com.puffin.articles.repository.ArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
		properties = "SpringBootTest.WebEnvironment.MOCK",
		classes = ArticlesApplication.class)
@AutoConfigureMockMvc
public class ArticleControllerTest {

	private JacksonTester<ArticleCreateTO> json;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ArticleRepository articleRepository;

	@Before
	public void setup() {
		ObjectMapper objectMapper = new ObjectMapper();
		JacksonTester.initFields(this, objectMapper);
	}

	@Test
	public void givenArticles_whenGetArticles_thenStatus200() throws Exception {

		mvc.perform(get("/articles")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().string(containsString("Yoga is cool")));
	}

	@Test
	public void givenArticle_whenRequestedById_thenArticleReturned() throws Exception {

		mvc.perform(get("/articles/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().string(containsString("Lets Go Surfing")));
	}

	@Test
	public void givenNewArticle_thenSaved() throws Exception {
		ArticleCreateTO articleCreateTO = buildIncomingArticle();

		String postResponse = mvc.perform(post("/articles")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.write(articleCreateTO).getJson()))
				.andExpect(status().isCreated())
				.andReturn().getResponse().getContentAsString();

		assertThat(articleRepository.findAll().size(), is(4));
	}

	@Test
	public void givenTags_WhenRequestedByName_thenSummaryReturned() throws Exception {

		String postResponse = mvc.perform(get("/tags/health/20180813")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().string(containsString("\"count\":2")))
				.andReturn().getResponse().getContentAsString();
	}

	private ArticleCreateTO buildIncomingArticle() {
		Set<String> tagNames = new HashSet<>();
		tagNames.add("health");
		tagNames.add("brandNewTag");
		ArticleCreateTO articleCreateTO = new ArticleCreateTO();
		articleCreateTO.setBody("some text, potentially containing simple markup about how potato chips are great");
		articleCreateTO.setTags(tagNames);
		articleCreateTO.setTitle("latest science shows that potato chips are better for you than sugar");
		return articleCreateTO;
	}
}
