package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Comment;
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
	
	public Optional<Comment> findCommentById(Integer id){
		return commentRepository.findById(id);
	}
	
	public List<Comment> findCommentByUser(User user) {
		return commentRepository.findByAuthor(user);
	}
	
	public Comment addComment(Comment c) {
		Comment comment = new Comment();
		comment.setCreated();
		comment.setAuthor(c.getAuthor());
		comment.setRoot(c.getRoot());
		return commentRepository.save(comment);
	}
	
	public Comment updateComment(Comment c) {
		return commentRepository.save(c);
	}
	
	public void deleteComment(Comment c) {
		commentRepository.delete(c);
	}
	
	
}
