package com.example.demo.post;

import com.example.demo.post.Post;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PostRequest {
    private Post post;
    private String photoPath;

    public PostRequest() {

    }

    public PostRequest(Post post, String photoPath) {
        this.post = post;
        this.photoPath = photoPath;
    }

    public Post getPost() {
        return post;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}

