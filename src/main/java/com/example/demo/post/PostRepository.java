package com.example.demo.post;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface PostRepository extends MongoRepository<Post, String>{
    List<Post> findByUsername(String username);
    List<Post> findByLocationId(String locationId);
}
