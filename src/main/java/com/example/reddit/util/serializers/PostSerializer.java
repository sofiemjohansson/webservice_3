package com.example.reddit.util.serializers;

import com.example.reddit.domain.Comment;
import com.example.reddit.domain.Post;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PostSerializer extends JsonSerializer<Post> {
    @Override
    public void serialize(Post post, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", post.getId());
        jsonGenerator.writeStringField("title", post.getTitle());
        jsonGenerator.writeStringField("text", post.getText());
        jsonGenerator.writeObjectField("rating", post.getRating());
        jsonGenerator.writeArrayFieldStart("comments");
        for (Comment comment : post.getComments()) {
            jsonGenerator.writeObject(comment);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeObjectFieldStart("user");
        jsonGenerator.writeNumberField("id", post.getUser().getId());
        jsonGenerator.writeStringField("username", post.getUser().getUsername());
        jsonGenerator.writeEndObject();
        jsonGenerator.writeObjectFieldStart("topic");
        jsonGenerator.writeNumberField("id", post.getTopic().getId());
        jsonGenerator.writeStringField("name", post.getTopic().getName());
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();
    }
}
