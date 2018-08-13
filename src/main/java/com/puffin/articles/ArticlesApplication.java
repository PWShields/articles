package com.puffin.articles;

import com.puffin.articles.domain.Article;
import com.puffin.articles.domain.Tag;
import com.puffin.articles.repository.ArticleRepository;
import com.puffin.articles.repository.TagRepository;
import org.h2.server.web.WebServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ArticlesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticlesApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ArticleRepository articleRepository, TagRepository tagRepository) {
		return args -> {

			articleRepository.deleteAllInBatch();
			tagRepository.deleteAllInBatch();

			Article article1 = new Article()
					.setTitle("Lets Go Surfing")
					.setDate(LocalDate.now())
					.setBody("Great winter surfing this year");
			Article article2 = new Article()
					.setTitle("Yoga is cool")
					.setDate(LocalDate.now())
					.setBody("Practice is a path");
			Article article3 = new Article()
					.setTitle("City to Surf 2018")
					.setDate(LocalDate.now())
					.setBody("14 kms in 45 minutes");

			Tag healthTag = new Tag("health");
			Tag fitnessTag = new Tag("fitness");
			Tag scienceTag = new Tag("science");

			tagRepository.save(healthTag);
			tagRepository.save(fitnessTag);
			tagRepository.save(scienceTag);

			article1.getTags().add(healthTag);
			article1.getTags().add(fitnessTag);
			article1.getTags().add(scienceTag);
			articleRepository.save(article1);
			Tag existingHealthTag = tagRepository.findByName("health");
			Tag existingFitnessTag = tagRepository.findByName("fitness");
			Tag existingScienceTag = tagRepository.findByName("science");
			article2.getTags().add(existingHealthTag);
			article2.getTags().add(existingFitnessTag);
			article3.getTags().add(existingScienceTag);

			articleRepository.save(article2);
			articleRepository.save(article3);

		}

				;
	}

	@Bean
	ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}


}


