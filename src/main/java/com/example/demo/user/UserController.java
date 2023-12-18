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
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{username}")
    public void deleteUser(@PathVariable String username) {
        boolean success = userService.deleteUser(username);
        System.out.println(success);
    }

    @PutMapping("/edit/{username}")
    public User updateUser(@PathVariable String username, @RequestBody UserRequest updatedUserRequest) {
        User updatedData = userService.addImage(updatedUserRequest.getUser(),updatedUserRequest.getProfilePicturePath());
        User user = userService.updateUser(username, updatedData.getName(), updatedData.getEmail(), updatedData.getBio(), updatedData.getImage());
        return user;
    }
}
/* UserRequest JSON example:
{"user":{"username":"CosminUsername5","password":"CosminPassword","name":"CosminName","email":"CosminEmail","bio":"CosminBio","image":"null","postIds":["6575c50399a7eb702d4a6cdd"],"followingIds":["6575140d3dda26791a0b78c1"],"online":true}, "profilePicturePath":"C:/Users/Armand/Pictures/Saved Pictures/dimi.jpg"}
 */
