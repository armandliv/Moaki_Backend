package com.example.demo.post;
import com.example.demo.user.UserService;
import com.example.demo.user.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    public List<Post> getPostsByLocation(String locationId) {
        return repository.findByLocationId(locationId);
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

    public Post updatePost(String id, String username, String locationId, String description, String image, int score, List<String> likeIds, List<String> commentIds) {
        Post post = repository.findById(id).orElse(null);
        if (post != null) {
            post.setUsername(username);
            post.setLocationId(locationId);
            post.setDescription(description);
            post.setImage(image);
            post.setScore(score);
            post.setLikeIds(likeIds);
            post.setCommentIds(commentIds);
            repository.save(post);
            return post;
        }
        return null;
    }

    public Post addImage(Post post, String imagePath) {
        Path path = Paths.get(imagePath);
        String image;
        try{
            image = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        }catch(IOException e){
            image = null;
        }
        post.setImage(image);
        return post;
    }

    public Post addLike(String postId, String username) {
        String userId = userService.getUserByUsername(username).getId();
        Post post = repository.findById(postId).orElse(null);
        if (post != null) {
            if(post.getLikeIds() == null)
            {
                post.setLikeIds(new ArrayList<>());
            }
            // check if user already liked the post
            if (post.getLikeIds().contains(userId)) {
                return null;
            }
            post.addLike(userId);
            repository.save(post);
            return post;
        }
        return null;
    }

    public Post removeLike(String postId, String username) {
        String userId = userService.getUserByUsername(username).getId();
        Post post = repository.findById(postId).orElse(null);
        if (post != null) {
            // check if user liked the post
            if (!post.getLikeIds().contains(userId)) {
                return null;
            }
            post.removeLike(userId);
            if(post.getLikeIds() == null)
            {
                post.setLikeIds(new ArrayList<>());
            }
            repository.save(post);
            return post;
        }
        return null;
    }

    public Post addComment(String postId, String commentId) {
        Post post = repository.findById(postId).orElse(null);
        if (post != null) {
            if(post.getCommentIds() == null)
            {
                post.setCommentIds(new ArrayList<>());
            }
            post.addCommentId(commentId);
            repository.save(post);
            return post;
        }
        return null;
    }

    public Post removeComment(String postId, String commentId) {
        Post post = repository.findById(postId).orElse(null);
        if (post != null) {
            post.removeCommentId(commentId);
            if(post.getCommentIds() == null)
            {
                post.setCommentIds(new ArrayList<>());
            }
            repository.save(post);
            return post;
        }
        return null;
    }

    public boolean isLiked(String postId, String username) {
        String userId = userService.getUserByUsername(username).getId();
        Post post = repository.findById(postId).orElse(null);
        if (post != null) {
            if(post.getLikeIds() == null)
            {
                post.setLikeIds(new ArrayList<>());
            }
            return post.getLikeIds().contains(userId);
        }
        return false;
    }


}
