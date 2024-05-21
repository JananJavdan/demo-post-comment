package com.example.jpa.config;

import com.example.jpa.model.Comment;
import com.example.jpa.model.Post;
import com.example.jpa.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class PostConfig {

    @Bean
    CommandLineRunner clr(PostRepository repository) {

        return (args) -> {
            String randomTitle = generateRandomTitle();

            Post post = new Post(randomTitle, "post description", "post content"); // Create Comments
            Comment comment1 = new Comment("Great Post!");
            comment1.setPost(post);
            Comment comment2 = new Comment("Really helpful Post. Thanks a lot!");
            comment2.setPost(post); // Add comments in the
            post.getComments().add(comment1);
            post.getComments().add(comment2);
            repository.save(post);
        };
    }
    private String generateRandomTitle() {
        String baseTitle = "PostTitle";
        int postId = new Random().nextInt(10);
        return baseTitle + "_" + postId;
    }
}
