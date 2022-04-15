package com.revature.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Post;
import com.revature.repos.PostRepository;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http;//localhost:4200")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Post> findAllPost() {
		return postRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Post>> findPostById(@PathVariable Integer id) {
		Optional<Post> post = postRepository.findById(id);
		return ResponseEntity.ok().body(post);
	}

	@PostMapping("/add")
	public ResponseEntity<Post> addComment(@RequestBody Post p) {
		Post post = postRepository.save(p);
		post.setPosted();
		post.setAuthor(p.getAuthor());
		post.setComments(p.getComments());
		return new ResponseEntity<>(post, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody Post p) {
		Post post = postRepository.findById(id).get();
		post.setDescription(p.getDescription());
		post.setTitle(p.getTitle());
		post.setPosted();
		Post updatePost = postRepository.save(post);
		return ResponseEntity.ok(updatePost);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deletPost(@PathVariable(value = "id") Integer id) {
		Post post = postRepository.findById(id).get();
		postRepository.delete(post);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
