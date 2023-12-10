package com.example.demo.comment;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping(path = "comment")
public class CommentController {
        private final CommentService commentService;

        @Autowired
        public CommentController(CommentService commentService){
            this.commentService = commentService;
        }

        @GetMapping("/comments")
        public List<Comment> getComments() {
            return commentService.getComments();
        }

        @GetMapping("/get/{id}")
        public Comment getCommentById(@PathVariable String id) {
            Comment comment = commentService.getCommentById(id);
            return comment;
        }

        @PostMapping("/add")
        public Comment createComment(@RequestBody Comment newComment) {
            Comment createdComment = commentService.createComment(newComment);
            return createdComment;
        }

        @DeleteMapping("/delete/{id}")
        public boolean deleteComment(@PathVariable String id) {
            boolean success = commentService.deleteComment(id);
            return success;
        }

        @PutMapping("/edit/{id}")
        public Comment updateComment(@PathVariable String id, @RequestBody Comment updatedData) {
            Comment comment = commentService.updateComment(id, updatedData.getContent());
            return comment;
        }
}
