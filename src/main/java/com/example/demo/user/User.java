package com.example.demo.user;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;
@Document
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String bio;
    //private String profilePicturePath;
    private byte[] image;
    private boolean isOnline;
    private List<String> postIds;
    private List<String> followingIds;

    public User() {

    }

    public User(String id, String username, String password, String name, String email, String bio, byte[] image , boolean isOnline, List<String> postIds, List<String> followingIds) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.image = image;
        this.isOnline = isOnline;
        this.postIds = postIds;
        this.followingIds = followingIds;
    }

    public User(String username, String password, String name, String email, String bio, byte[] image, boolean isOnline, List<String> postIds, List<String> followingIds) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.image = image;
        this.isOnline = isOnline;
        this.postIds = postIds;
        this.followingIds = followingIds;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public byte[] getImage() {
        return image;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public List<String> getPostIds() {
        return postIds;
    }

    public List<String> getFollowingIds() {
        return followingIds;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public void setPostIds(List<String> postIds) {
        this.postIds = postIds;
    }

    public void setFollowingIds(List<String> followingIds) {
        this.followingIds = followingIds;
    }

    public void addPostId(String postId) {
        this.postIds.add(postId);
    }

    public void addFollowingId(String followingId) {
        this.followingIds.add(followingId);
    }

    public void removePostId(String postId) {
        this.postIds.remove(postId);
    }

    public void removeFollowingId(String followingId) {
        this.followingIds.remove(followingId);
    }

    public void update(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
        this.bio = user.getBio();
        this.image = user.getImage();
        this.isOnline = user.isOnline();
        this.postIds = user.getPostIds();
        this.followingIds = user.getFollowingIds();
    }

    //    @Override
    //    public String toString() {
    //        return "User{" +
    //                "id=" + id +
    //                ", name='" + name + '\'' +
    //                ", email='" + email + '\'' +
    //                ", dob=" + dob +
    //                '}';
    //    }
}


