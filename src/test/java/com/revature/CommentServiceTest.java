package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.models.Comment;
import com.revature.models.Post;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repos.CommentRepository;
import com.revature.services.CommentService;

@SpringBootTest
public class CommentServiceTest {

	
	@InjectMocks
	private CommentService commentService;
	
	@Mock
	private CommentRepository commentRepository;
	
	@Before(value = "")
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void getCommentsTest() {
		List<Comment> comments = new ArrayList<Comment>();
		User userNurse = new User("1", "Name", "Last", "Email", new Role(1, "nurse"));
		User userDoctor = new User("2", "Name", "Last", "Email", new Role(2, "doctor"));
		Post post = new Post(1, "This is the title", "This is a description", LocalDateTime.now(), comments, userNurse);
		Post post2 = new Post(2, "This is the title", "This is a description", LocalDateTime.now(), comments, userDoctor);
		Comment comment = new Comment(100, "This is a comment", LocalDateTime.now(), userNurse, post);
		Comment comment2 = new Comment(200, "This is a second comment", LocalDateTime.now(), userDoctor, post2);
		comments.add(comment);
		comments.add(comment2);
		when(commentRepository.findAll()).thenReturn(comments);
		
		List<Comment> commentsList = commentService.findAllComments();
		
		assertEquals(2, commentsList.size());
		verify(commentRepository, times(1)).findAll();	
		
	}
}
