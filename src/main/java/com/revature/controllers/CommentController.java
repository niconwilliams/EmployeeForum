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
import com.revature.services.CommentService;
import com.revature.services.PostService;
import com.revature.services.UserService;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins ="http;//localhost:4200")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<Comment> findAll(){
		return commentService.findAllComments();
	}
	
	@GetMapping("/{id}")
	public Comment findComment(@PathVariable Integer id) {
		return commentService.findCommentById(id);
	}
	
	@GetMapping("user/{id}")
	public List<Comment> findCommentsByUser(@PathVariable String id) {
		return commentService.findCommentByUser(userService.findByUserId(id).get());
	}
	
	@GetMapping("users/{id}")
	public List<Comment> findByPost(@PathVariable Integer id) {
		return commentService.findByPost(postService.findPostById(id));
	}
	
	@PostMapping("/add")
	public Comment add(@RequestParam String body, @RequestParam String userId, @RequestParam int pId) {
		Comment c = new Comment();
		c.setBody(body);
		c.setAuthor(userService.findByUserId(userId).get());
		c.setCreated();
		c.setRoot(postService.findPostById(pId));
		commentService.addComment(c);
		return c;
	}
	
	@PutMapping("/{id}")
	public Comment updateComment(@PathVariable Integer id, @RequestBody Comment c) {
		commentService.updateComment(c);
		return commentService.findCommentById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteComment(@PathVariable Integer Id, @RequestBody Comment c) {
		 commentService.deleteComment(c);
	}
}
