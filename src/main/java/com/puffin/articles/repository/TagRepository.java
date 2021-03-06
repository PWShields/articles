package com.puffin.articles.repository;

import com.puffin.articles.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {


	Tag findByName(String health);
}
