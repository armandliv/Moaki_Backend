package com.example.demo.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;


import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/get/{username}")
    public User getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return user;
    }

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody UserRequest newUserRequest) {
        User newUser = userService.addImage(newUserRequest.getUser(),newUserRequest.getProfilePicturePath());
        User createdUser = userService.createUser(newUser);
        // createdUser is null if the username already exists
        if (createdUser == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username, @RequestHeader("X-Username") String loggedInUsername) {
        if (!userService.isLoggedInUser(username,loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        boolean success = userService.deleteUser(username);
        System.out.println(success);
        if (success) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/edit/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody UserRequest updatedUserRequest, @RequestHeader("X-Username") String loggedInUsername) {
        if (!userService.isLoggedInUser(username,loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User updatedData = userService.addImage(updatedUserRequest.getUser(),updatedUserRequest.getProfilePicturePath());
        User user = userService.updateUser(username, updatedData.getName(), updatedData.getEmail(), updatedData.getBio(), updatedData.getImage());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/follow/{username}/{usernameToFollow}")
    public ResponseEntity<User> followUser(@PathVariable String username, @PathVariable String usernameToFollow, @RequestHeader("X-Username") String loggedInUsername) {
        if (!userService.isLoggedInUser(username,loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User user = userService.followUser(username, usernameToFollow);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/unfollow/{username}/{usernameToUnfollow}")
    public ResponseEntity<User> unfollowUser(@PathVariable String username, @PathVariable String usernameToUnfollow, @RequestHeader("X-Username") String loggedInUsername) {
        if (!userService.isLoggedInUser(username,loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User user = userService.unfollowUser(username, usernameToUnfollow);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/addPost/{username}/{postId}")
    public ResponseEntity<User> addPost(@PathVariable String username, @PathVariable String postId, @RequestHeader("X-Username") String loggedInUsername) {
        if (!userService.isLoggedInUser(username,loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User user = userService.addPostId(username, postId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/removePost/{username}/{postId}")
    public ResponseEntity<User> removePost(@PathVariable String username, @PathVariable String postId, @RequestHeader("X-Username") String loggedInUsername) {
        if (!userService.isLoggedInUser(username,loggedInUsername)) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User user = userService.removePostId(username, postId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/isFollowing/{username}/{followedUsername}")
    public boolean isFollowing(@PathVariable String username, @PathVariable String followedUsername) {
        return userService.isFollowing(username, followedUsername);
    }

    @GetMapping("/getFollowing/{username}")
    public List<String>getFollowers(@PathVariable String username) {
        return userService.getFollowingUsernames(username);
    }

    @PostMapping("/login")
    public ResponseEntity<User>login(@RequestBody User userRequest) {
        User user = userService.login(userRequest.getUsername(), userRequest.getPassword());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
/* UserRequest JSON example:
{"user":{"username":"CosminUsername5","password":"CosminPassword","name":"CosminName","email":"CosminEmail","bio":"CosminBio","image":"null","postIds":["6575c50399a7eb702d4a6cdd"],"followingIds":["6575140d3dda26791a0b78c1"],"online":true}, "profilePicturePath":"C:/Users/Armand/Pictures/Saved Pictures/dimi.jpg"}
 */
