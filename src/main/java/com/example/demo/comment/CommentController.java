package com.example.demo.comment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.HttpStatus;

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
        public ResponseEntity<Comment> createComment(@RequestBody Comment newComment, @RequestHeader("X-Username") String loggedInUsername) {
            if(!commentService.isCommentOfLoggedInUser(newComment, loggedInUsername)) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(commentService.createComment(newComment), HttpStatus.CREATED);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteComment(@PathVariable String id, @RequestHeader("X-Username") String loggedInUsername) {
            if(!commentService.isCommentIdOfLoggedInUser(id, loggedInUsername)) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
            boolean success = commentService.deleteComment(id);
            if(success)
            {
                return new ResponseEntity<>(null,HttpStatus.OK);
            }
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        @PutMapping("/edit/{id}")
        public ResponseEntity<Comment> updateComment(@PathVariable String id, @RequestBody Comment updatedData, @RequestHeader("X-Username") String loggedInUsername) {
            if(!commentService.isCommentIdOfLoggedInUser(id, loggedInUsername)) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(commentService.updateComment(id, updatedData.getContent()), HttpStatus.OK);
        }
}
