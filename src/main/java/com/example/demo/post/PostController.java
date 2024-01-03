package com.example.demo.post;

import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Post> getPostsByUsername(@PathVariable String username) {
        return postService.getUserPosts(username);
    }

    @GetMapping("/userFeed/{username}")
    public List<Post> getFollowingPosts(@PathVariable String username) {
        return postService.getFollowingPosts(username);
    }

    @GetMapping("/morePosts/{username}")
    public List<Post> getMorePosts(@PathVariable String username) {
        return postService.getMorePosts(username);
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
    public Post createPost(@RequestBody Post newPostRequest) {
        return postService.createPost(newPostRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable String id) {
        boolean success = postService.deletePost(id);
        System.out.println(success);
    }

    @PutMapping("/edit/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post updatedData) {
        return postService.updatePost(id, updatedData.getUsername(), updatedData.getLocationId(), updatedData.getDescription(), updatedData.getImage(), updatedData.getScore(), updatedData.getLikeIds(), updatedData.getCommentIds());
    }

    @GetMapping("addLike/{postId}/{username}")
    public Post addLike(@PathVariable String postId, @PathVariable String username) {
        return postService.addLike(postId, username);
    }

    @GetMapping("removeLike/{postId}/{username}")
    public Post removeLike(@PathVariable String postId, @PathVariable String username) {
        return postService.removeLike(postId, username);
    }

    @GetMapping("addComment/{postId}/{commentId}")
    public Post addComment(@PathVariable String postId, @PathVariable String commentId) {
        return postService.addComment(postId, commentId);
    }

    @GetMapping("removeComment/{postId}/{commentId}")
    public Post removeComment(@PathVariable String postId, @PathVariable String commentId) {
        return postService.removeComment(postId, commentId);
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