package com.example.reddit.util.serializers;

import com.example.reddit.domain.Comment;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class CommentSerializer extends JsonSerializer<Comment> {
    @Override
    public void serialize(Comment comment, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", comment.getId());
        jsonGenerator.writeStringField("text", comment.getText());
        jsonGenerator.writeObjectFieldStart("post");
        jsonGenerator.writeNumberField("id", comment.getPost().getId());
        jsonGenerator.writeStringField("title", comment.getPost().getTitle());
        jsonGenerator.writeEndObject();
        jsonGenerator.writeObjectFieldStart("user");
        jsonGenerator.writeNumberField("id", comment.getUser().getId());
        jsonGenerator.writeStringField("username", comment.getUser().getUsername());
        jsonGenerator.writeEndObject();
        jsonGenerator.writeArrayFieldStart("children");
        for(Comment c: comment.getComments()) jsonGenerator.writeObject(c);
        jsonGenerator.writeEndArray();
        jsonGenerator.writeObjectField("rating", comment.getRating());
        jsonGenerator.writeStringField("status", comment.getStatus().name());
        jsonGenerator.writeEndObject();
    }
}
