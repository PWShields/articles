package com.puffin.articles;

import com.puffin.articles.domain.Article;
import com.puffin.articles.repository.ArticleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ArticlesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticlesApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ArticleRepository articleRepository) {
		return args -> {

			List<String> tags3 = Arrays.asList("health, fitness, science");
			List<String> tags2 = Arrays.asList("health, fitness");
			List<String> tags1 = Arrays.asList("health");

			if (articleRepository.findAll().isEmpty()) {

				Article article1 = new Article()
						.setTitle("Lets Go Surfing")
						.setDate(LocalDate.now())
						.setBody("Great winter surfing this year")
						.setTags(tags3);

				Article article2 = new Article()
						.setTitle("Yoga is cool")
						.setDate(LocalDate.now())
						.setBody("Practice is a path")
						.setTags(tags2);

				Article article3 = new Article()
						.setTitle("City to Surf 2018")
						.setDate(LocalDate.now())
						.setBody("14 kms in 45 minutes")
						.setTags(tags1);

				 articleRepository.save(article1);
				 articleRepository.save(article2);
				 articleRepository.save(article3);
			}
		};
	}


}


