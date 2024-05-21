package com.example.jpa.controller;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Post;
import com.example.jpa.repository.PostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/list")
    public String listPosts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "postList"; // Assuming this is the correct view name
    }

    @GetMapping("/new")
    public String showNewPostForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "newPost";
    }

    @PostMapping("/save")
    public String createPost(@Valid Post post) {
        postRepository.save(post);
        return "redirect:/posts/list";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
        model.addAttribute("post", post);
        return "editPost";
    }


    @GetMapping("/view/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
        model.addAttribute("post", post);
        return "viewPost";
    }

    @PutMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id, @Valid Post post) {
        // Retrieve the existing post by ID
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));

        // Update the existing post with the new data
        existingPost.setTitle(post.getTitle());
        existingPost.setDescription(post.getDescription());
        existingPost.setContent(post.getContent());

        // Save the updated post
        postRepository.save(existingPost);

        // Redirect to the post list page
        return "redirect:/posts/list";
    }

    @GetMapping ("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        // Delete the post with the specified ID
        postRepository.deleteById(id);

        // Redirect to the post list page after deletion
        return "redirect:/posts/list"; // Make sure the URL is correct
    }
}
