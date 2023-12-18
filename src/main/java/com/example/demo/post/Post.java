package com.example.demo.post;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
@Document
public class Post {
    @Id
    private String id;
    private String username;
    private String locationId;
    private String description;
    private byte[] image;
    private int score;
    private int likes;
    private List<String> commentIds;

    public Post() {

    }

    public Post(String id, String username, String locationId, String description, byte[] image, int score, int likes, List<String> commentIds) {
        this.id = id;
        this.username = username;
        this.locationId = locationId;
        this.description = description;
        this.image = image;
        this.score = score;
        this.likes = likes;
        this.commentIds = commentIds;
    }

    public Post(String username, String locationId, String description, byte[] image, int score, int likes, List<String> commentIds) {
        this.username = username;
        this.locationId = locationId;
        this.description = description;
        this.image = image;
        this.score = score;
        this.likes = likes;
        this.commentIds = commentIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<String> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<String> commentIds) {
        this.commentIds = commentIds;
    }

    public void addCommentId(String commentId) {
        this.commentIds.add(commentId);
    }

    public void removeCommentId(String commentId) {
        this.commentIds.remove(commentId);
    }

    public void incrementLikes() {
        this.likes++;
    }

    public void decrementLikes() {
        this.likes--;
    }

    public void update(Post post) {
        this.username = post.getUsername();
        this.locationId = post.getLocationId();
        this.description = post.getDescription();
        this.image = post.getImage();
        this.score = post.getScore();
        this.likes = post.getLikes();
        this.commentIds = post.getCommentIds();
    }
}
