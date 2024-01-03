package com.example.demo.post;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.ArrayList;
@Document
public class Post {
    @Id
    private String id;
    private String username;
    private String locationId;
    private String description;
    private String image;
    private int score;
    private List<String> likeIds;
    private List<String> commentIds;

    public Post() {

    }

    public Post(String id, String username, String locationId, String description, String image, int score, List<String> likeIds, List<String> commentIds) {
        this.id = id;
        this.username = username;
        this.locationId = locationId;
        this.description = description;
        this.image = image;
        this.score = score;
        this.likeIds = likeIds;
        this.commentIds = commentIds;
    }

    public Post(String username, String locationId, String description, String image, int score, List<String> likeIds, List<String> commentIds) {
        this.username = username;
        this.locationId = locationId;
        this.description = description;
        this.image = image;
        this.score = score;
        this.likeIds = likeIds;
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

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumberOfLikes() {
        if(likeIds == null) {
            this.likeIds = new ArrayList<>();
            return 0;
        }
        return likeIds.size();
    }

    public List<String> getLikeIds() {
        if(likeIds == null) {
            this.likeIds = new ArrayList<>();
        }
        return likeIds;
    }

    public void setLikeIds(List<String>likeIds) { this.likeIds = likeIds; }

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

    public void addLike(String userId) { this.likeIds.add(userId); }

    public void removeLike(String userId) { this.likeIds.remove(userId); }

    public void update(Post post) {
        this.username = post.getUsername();
        this.locationId = post.getLocationId();
        this.description = post.getDescription();
        this.image = post.getImage();
        this.score = post.getScore();
        this.likeIds = post.getLikeIds();
        this.commentIds = post.getCommentIds();
    }
}
