package com.example.demo.post;

import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/generalFeed")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/userProfile/{username}")
    public ResponseEntity<List<Post>> getPostsByUsername(@PathVariable String username, @RequestHeader("X-Username") String loggedInUsername) {
        if(!postService.isLoggedInUser(username,loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(postService.getUserPosts(username), HttpStatus.OK);
    }

    @GetMapping("/userFeed/{username}")
    public ResponseEntity<List<Post>> getFollowingPosts(@PathVariable String username, @RequestHeader("X-Username") String loggedInUsername) {
        if(!postService.isLoggedInUser(username,loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(postService.getFollowingPosts(username), HttpStatus.OK);
    }

    @GetMapping("/morePosts/{username}")
    public ResponseEntity<List<Post>> getMorePosts(@PathVariable String username, @RequestHeader("X-Username") String loggedInUsername) {
        if(!postService.isLoggedInUser(username,loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(postService.getMorePosts(username), HttpStatus.OK);
    }

    @GetMapping("/get/location/{locationId}")
    public List<Post> getPostsByLocation(@PathVariable String locationId) {
        return postService.getPostsByLocation(locationId);
    }

    @GetMapping("/get/{id}")
    public Post getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Post> createPost(@RequestBody Post newPostRequest, @RequestHeader("X-Username") String loggedInUsername) {
        if(!postService.isPostOfLoggedInUser(newPostRequest, loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(postService.createPost(newPostRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable String id, @RequestHeader("X-Username") String loggedInUsername) {
        if(!postService.isPostIdOfLoggedInUser(id, loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        boolean success = postService.deletePost(id);
        System.out.println(success);
        if(success) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post updatedData, @RequestHeader("X-Username") String loggedInUsername) {
        if(!postService.isPostIdOfLoggedInUser(id, loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(postService.updatePost(id, updatedData.getUsername(), updatedData.getLocationId(), updatedData.getDescription(), updatedData.getImage(), updatedData.getScore(), updatedData.getLikeIds(), updatedData.getCommentIds()), HttpStatus.OK);
    }

    @GetMapping("addLike/{postId}/{username}")
    public ResponseEntity<Post> addLike(@PathVariable String postId, @PathVariable String username, @RequestHeader("X-Username") String loggedInUsername) {
        if(!postService.isPostIdOfLoggedInUser(postId, loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(postService.addLike(postId, username), HttpStatus.OK);
    }

    @GetMapping("removeLike/{postId}/{username}")
    public ResponseEntity<Post> removeLike(@PathVariable String postId, @PathVariable String username, @RequestHeader("X-Username") String loggedInUsername) {
        if(!postService.isPostIdOfLoggedInUser(postId, loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(postService.removeLike(postId, username), HttpStatus.OK);
    }

    @GetMapping("addComment/{postId}/{commentId}")
    public Post addComment(@PathVariable String postId, @PathVariable String commentId) {
        return postService.addComment(postId, commentId);
    }

    @GetMapping("removeComment/{postId}/{commentId}")
    public ResponseEntity<Post> removeComment(@PathVariable String postId, @PathVariable String commentId, @RequestHeader("X-Username") String loggedInUsername) {
        if(!postService.isCommentOfLoggedInUser(commentId, loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(postService.removeComment(postId, commentId), HttpStatus.OK);
    }

    @GetMapping("/isLiked/{postId}/{username}")
    public boolean isLiked(@PathVariable String postId, @PathVariable String username) {
        return postService.isLiked(postId, username);
    }

    @GetMapping("/getNumberOfLikes/{postId}")
    public int getNumberOfLikes(@PathVariable String postId) {
        return postService.getNumberOfLikes(postId);
    }

    @GetMapping("/getLikes/{postId}")
    public List<String> getLikes(@PathVariable String postId) {
        return postService.getLikes(postId);
    }
}
/* example of a post:
{"username":"CosminUsername5","locationId":"CosminLocationId","description":"CosminDescription","image":null,"score":5,"likeIds":["6575140d3dda26791a0b78c1"],"commentIds":["1","2","3"]}
with post request:
{"post":{"username":"CosminUsername5","locationId":"CosminLocationId","description":"CosminDescription","image":null,"score":5,"likes":1,"commentIds":["1","2","3"]}, "photoPath":"C:/Users/Armand/Pictures/Saved Pictures/dimi.jpg"}
 */