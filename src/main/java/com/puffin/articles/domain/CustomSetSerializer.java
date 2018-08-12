package com.puffin.articles.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomSetSerializer extends StdSerializer<Set<Tag>>{

	public CustomSetSerializer() {
		this(null);
	}

	protected CustomSetSerializer(Class<Set<Tag>> t) {
		super(t);
	}


	@Override
	public void serialize(
			Set<Tag> tags,
			JsonGenerator generator,
			SerializerProvider provider)
			throws IOException, JsonProcessingException {

		List<String> tagNames = new ArrayList<>();
		for (Tag tag : tags) {
			tagNames.add(tag.getName());
		}
		generator.writeObject(tagNames);
	}

}
