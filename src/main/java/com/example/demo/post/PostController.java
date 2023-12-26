package com.example.demo.post;

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
        return postService.updatePost(id, updatedData.getUsername(), updatedData.getLocationId(), updatedData.getDescription(), updatedData.getImage(), updatedData.getScore(), updatedData.getLikes(), updatedData.getCommentIds());
    }
}
/* example of a post request:
{"post":{"username":"CosminUsername5","locationId":"CosminLocationId","description":"CosminDescription","image":null,"score":5,"likes":1,"commentIds":["1","2","3"]}, "photoPath":"C:/Users/Armand/Pictures/Saved Pictures/dimi.jpg"}
 */