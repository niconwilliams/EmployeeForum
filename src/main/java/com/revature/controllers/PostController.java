package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Comment;
import com.revature.models.Post;
import com.revature.services.CommentService;
import com.revature.services.PostService;
import com.revature.services.UserService;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http;//localhost:4200")
public class PostController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@GetMapping
	public List<Post> findAll() {
		return postService.findAllPost();
	}

	@GetMapping("/{id}")
	public Post findPost(@PathVariable Integer id) {
		return postService.findPostById(id);
	}

	@PostMapping("/add")
	public Post add(@RequestParam String title, @RequestParam String userId, @RequestParam String description) {
		Post p = new Post();
		p.setTitle(title);
		p.setDescription(description);
		p.setPosted();
		p.setAuthor(userService.findByUserId(userId).get()); 
		postService.addPost(p);
		return p;
	}

	@PutMapping("/{id}")
	public Post updatePost(@PathVariable Integer id, @RequestBody String description, @RequestBody String title) {
		Post p = new Post();
		p = postService.findPostById(id);
		p.setTitle(title);
		p.setDescription(description);
		postService.updatePost(p);
		return postService.findPostById(id);
	}

	@DeleteMapping("/{id}")
	public void deletePost(@PathVariable Integer id, @RequestBody Post p) {
		List<Comment> comments = postService.findPostById(id).getComments();
		for(Comment c: comments) {
			commentService.deleteComment(c);
		}
		postService.deletePost(p);
	}

}
