package com.puffin.articles.domain;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Tag {

	String name;
	Integer count;
	List<String> articles;
	Set<String> related_tags;


}
