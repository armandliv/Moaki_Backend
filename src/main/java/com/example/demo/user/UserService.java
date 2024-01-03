package com.example.demo.user;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UserService {
    private final UserRepository repository;
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserByUsername(String username) {
        return repository.findByUsername(username);
    }
    public User getUserById(String id) {
        return repository.findById(id).orElse(null);
    }

    public User createUser(User newUser) {
        return repository.insert(newUser);
    }

    public boolean deleteUser(String username) {
        User user = repository.findByUsername(username);
        if (user != null) {
            repository.delete(user);
            return true;
        }
        return false;
    }

    public User updateUser(String username, String name, String email, String bio, String image) {
        User user = repository.findByUsername(username);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setBio(bio);
            user.setImage(image);
            repository.save(user);
            return user;
        }
        return null;
    }

    public User addImage(User user, String imagePath) {
        Path path = Paths.get(imagePath);
        String image;
        try{
            image = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        }catch(IOException e){
            image = null;
        }
        user.setImage(image);
        return user;
    }

    public User followUser(String username, String followedUsername) {
        User user = repository.findByUsername(username);
        User followedUser = repository.findByUsername(followedUsername);
        if (user != null && followedUser != null) {
            if(user.getFollowingIds() == null)
            {
                user.setFollowingIds(new ArrayList<>());
            }
            user.addFollowingId(followedUser.getId());
            repository.save(user);
            return user;
        }
        return null;
    }

    public User unfollowUser(String username, String followedUsername) {
        User user = repository.findByUsername(username);
        User followedUser = repository.findByUsername(followedUsername);
        if (user != null && followedUser != null) {
            user.removeFollowingId(followedUser.getId());
            if(user.getFollowingIds() == null)
            {
                user.setFollowingIds(new ArrayList<>());
            }
            repository.save(user);
            return user;
        }
        return null;
    }

}

//new User(
//        "CosminId",
//        "CosminUsername",
//        "CosminPassword",
//        "CosminName",
//        "CosminEmail",
//        "CosminBio",
//        "CosminProfilePicturePath",
//        true,
//        List.of("1", "2", "3"),
//        List.of("1", "2", "3")
//        )
//
//return List.of(
//
//        );