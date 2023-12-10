package com.example.demo.comment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {
    @Id
    private String id;
    private String username;
    private String content;

    public Comment() {

    }

    public Comment(String id, String username, String content) {
        this.id = id;
        this.username = username;
        this.content = content;
    }

    public Comment(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void update(Comment comment) {
        this.username = comment.getUsername();
        this.content = comment.getContent();
    }
}
