package com.example.demo.post;
import com.example.demo.user.UserService;
import com.example.demo.user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class PostService {
    private final PostRepository repository;
    private final UserService userService;
    @Autowired
    public PostService(PostRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public List<Post> getPosts() {
        return repository.findAll();
    }

    public List<Post> getUserPosts(String username) {
        return repository.findByUsername(username);
    }

    public List<Post> getFollowingPosts(String username) {
        List<Post> posts = new ArrayList<>();
        User user = userService.getUserByUsername(username);
        for(String userId: user.getFollowingIds())
        {
            System.out.println(userId);
            User followedUser = userService.getUserById(userId);
            posts.addAll(getUserPosts(followedUser.getUsername()));
        }
        return posts;
    }

    public Post getPostById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Post createPost(Post newPost) {
        return repository.insert(newPost);
    }

    public boolean deletePost(String id) {
        Post post = repository.findById(id).orElse(null);
        if (post != null) {
            repository.delete(post);
            return true;
        }
        return false;
    }

    public Post updatePost(String id, String username, String locationId, String description, byte[] image, int score, int likes, List<String> commentIds) {
        Post post = repository.findById(id).orElse(null);
        if (post != null) {
            post.setUsername(username);
            post.setLocationId(locationId);
            post.setDescription(description);
            post.setImage(image);
            post.setScore(score);
            post.setLikes(likes);
            post.setCommentIds(commentIds);
            repository.save(post);
            return post;
        }
        return null;
    }

    public Post addImage(Post post, String imagePath) {
        Path path = Paths.get(imagePath);
        byte[] image;
        try{
            image = Files.readAllBytes(path);
        }catch(IOException e){
            image = null;
        }
        post.setImage(image);
        return post;
    }
}
