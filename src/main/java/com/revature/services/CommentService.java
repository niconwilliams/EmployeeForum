package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Comment;
import com.revature.models.Post;
import com.revature.models.User;
import com.revature.repos.CommentRepository;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	
	@Autowired
	public CommentService(CommentRepository commentRepository) {
		super();
		this.commentRepository = commentRepository;
	}
	
	public List<Comment> findAllComments(){
		return commentRepository.findAll();
	}
	
	public Comment findCommentById(Integer id){
		return commentRepository.getById(id);
	}
	
	public List<Comment> findCommentByUser(User user) {
		return commentRepository.findByAuthor(user);
	}
	
	public List<Comment> findByPost(Post post) {
		return commentRepository.findByRoot(post);
	}
	
	public void addComment(Comment c) {
		commentRepository.save(c);
	}
	
	public void updateComment(Comment c) {
		commentRepository.save(c);
	}
	
	public void deleteComment(Comment c) {
		commentRepository.delete(c);
	}
	
	
}
