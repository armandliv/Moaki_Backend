package com.example.demo.comment;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@Service
public class CommentService {
    private final CommentRepository repository;
    @Autowired
    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }
    public List<Comment> getComments() {
        return repository.findAll();
    }
    public Comment getCommentById(String id) {
        return repository.findById(id).orElse(null);
    }
    public Comment createComment(Comment newComment) {
        return repository.insert(newComment);
    }
    public boolean deleteComment(String id) {
        Comment comment = repository.findById(id).orElse(null);
        if (comment != null) {
            repository.delete(comment);
            return true;
        }
        return false;
    }
    public Comment updateComment(String id, String content) {
        Comment comment = repository.findById(id).orElse(null);
        if (comment != null) {
            comment.setContent(content);
            repository.save(comment);
            return comment;
        }
        return null;
    }
}
