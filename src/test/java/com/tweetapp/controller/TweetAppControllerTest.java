package com.tweetapp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.tweetapp.model.Comments;
import com.tweetapp.model.Tweets;
import com.tweetapp.model.UserRegistration;
import com.tweetapp.model.UserRequest;
import com.tweetapp.model.UserResponse;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UserRegistrationRepository;
import com.tweetapp.service.CommentService;
import com.tweetapp.service.SequenceGeneratorService;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserRegistrationService;
import com.tweetapp.utils.JwtUtil;


@SpringBootTest
@AutoConfigureMockMvc
 class TweetAppControllerTest {


	
	@Autowired
	private TweetAppController tweetAppController;
	
	@MockBean
	private UserRegistrationService userRegistrationService;
	
	@MockBean
	private TweetService tweetService;
	
	@MockBean
	private JwtUtil util;
	
	@MockBean
	private SequenceGeneratorService sequenceGeneratorService;
	
	@MockBean
	private CommentService commentService;
	
	@MockBean
	private UserRegistrationRepository userRegistrationRepository;
	//private UserRegistration userRegistration;
	@MockBean
	private TweetRepository tweetRepository;

	
	//register user
	@Test
	 void registerUser() throws Exception {
		UserRegistration userRegistration=new UserRegistration("Swagatika123","Swagatika","Mishra","swagatika@123","abc","abc", (long) 798438573);
		ResponseEntity<Object> responseEntity=new ResponseEntity<Object> ("User created successfully",HttpStatus.CREATED);
		BindingResult result = mock(BindingResult.class);
		
		assertThat(tweetAppController.userRegistration(userRegistration, result)).isEqualTo(responseEntity);
	}
	
	
	//login user
	@Test
	 void loginUser() {
		UserRequest userRequest=new UserRequest();
		userRequest.setLoginId("Swagatika123");
		userRequest.setPassword("abc");
		when(userRegistrationService.loginUser("Swagatika123", "abc")).thenReturn(true);
		String token=util.generateToken(userRequest.getLoginId());
		ResponseEntity<UserResponse> userResponse=new ResponseEntity<UserResponse> (new UserResponse(userRequest.getLoginId(),token,"success"),HttpStatus.ACCEPTED);
		assertThat(tweetAppController.userLogin(userRequest)).isEqualTo(userResponse);
		
	}
	
	//Test for forgot password
//	@Test
//	 void forgotPassword() {
//		UserRegistration userRegistration=new UserRegistration();
//		userRegistration.setLoginId("Swagatika123");
//		userRegistration.setPassword("adgc");
//		userRegistration.setConfirmPassword("adgc");
//		when(userRegistrationService.validateUsername("Swagatika123")).thenReturn(true);
//		when(userRegistrationService.forgotPassword("Swagatika123", "adgc")).thenReturn("password updated successfully!");
//		ResponseEntity<Object> userResponse=new ResponseEntity<Object> ("User successfully changed the password",HttpStatus.ACCEPTED);
//		assertThat(tweetAppController.forgotPassword("Swagatika123","adgc")).isEqualTo(userResponse);
//		
//	}
//	
//	@Test
//	 void forgotPassword_NegativeScenario() {
//		UserRegistration userRegistration=new UserRegistration();
//		userRegistration.setLoginId("Swagatika123");
//		userRegistration.setPassword("adgc");
//		userRegistration.setConfirmPassword("adgc");
//		when(userRegistrationService.validateUsername("Swagaika123")).thenReturn(false);
//		when(userRegistrationService.forgotPassword("Swagaika123", "adgc")).thenReturn("User with username not found!");
//		ResponseEntity<Object> userResponse=new ResponseEntity<Object> ("Not able to change the password.",HttpStatus.BAD_REQUEST);
//		assertThat(tweetAppController.forgotPassword("Swagaika123","adgc")).isEqualTo(userResponse);
//		
//	}
	
	//Test for post tweet
	@Test
	 void postTweet() throws Exception{
		Tweets tweet=new Tweets();
		tweet.setTweetId(sequenceGeneratorService.getSequenceNumber(Tweets.SEQUENCE_NAME));
		tweet.setTitle("God");
		tweet.setTweet("God is One, Almighty, Wisdom, Love, and more. (This is sometimes called “divine simplicity,” meaning that God and his attributes are perfect, whole, and unified.).");
		when(tweetService.validateUsername("Swagatika123")).thenReturn(true);
		BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(true);
		when(tweetService.postTweets("Swagatika123",tweet)).thenReturn(true);
		ResponseEntity<Object> userResponse=new ResponseEntity<Object> (tweet,HttpStatus.CREATED);
		assertThat(tweetAppController. postTweet("Swagatika123",tweet,result)).isEqualTo(userResponse);
	}
	
	@Test
	 void postTweet_negativeScenario() throws Exception{
		Tweets tweet=new Tweets();
		tweet.setTweetId(sequenceGeneratorService.getSequenceNumber(Tweets.SEQUENCE_NAME));
		tweet.setTitle("God");
		tweet.setTweet("God is One, Almighty, Wisdom, Love, and more. (This is sometimes called “divine simplicity,” meaning that God and his attributes are perfect, whole, and unified.).");
		when(tweetService.validateUsername("Swagaika123")).thenReturn(false);
		BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(true);
		when(tweetService.postTweets("Swagaika123",tweet)).thenReturn(false);
		ResponseEntity<Object> userResponse=new ResponseEntity<Object> ("Unable to post tweet",HttpStatus.BAD_REQUEST);
		assertThat(tweetAppController. postTweet("Swagaika123",tweet,result)).isEqualTo(userResponse);
	}
	
	//test getting all users
	@Test
	 void getAllUsers() {
		UserRegistration userRegistration_1=new UserRegistration("Swagatika123","Swagatika","Mishra","swagatika@123","abc","abc", (long) 798438573);
		UserRegistration userRegistration_2=new UserRegistration("Swati123","swati","Mishra","swati@123","abc","abc", (long) 798448573);
		List<UserRegistration> username=new ArrayList<>(Arrays.asList(userRegistration_1,userRegistration_2));
		when(userRegistrationRepository.findAllLoginIdsDetails()).thenReturn(username);
		when(userRegistrationService.getAllUsersWithUsername()).thenReturn(username);
		List<UserRegistration> usrnames=username;
		assertThat(tweetAppController.getAllUsers()).isEqualTo(usrnames);
		
	}
	
	@Test
	 void getAllUsers_negativescenario() {
		UserRegistration userRegistration_1=new UserRegistration("Swagatika123","Swagatika","Mishra","swagatika@123","abc","abc", (long) 798438573);
		UserRegistration userRegistration_2=new UserRegistration("Swati123","swati","Mishra","swati@123","abc","abc", (long) 798448573);
		List<UserRegistration> username=new ArrayList<>(Arrays.asList());
		when(userRegistrationRepository.findAllLoginIdsDetails()).thenReturn(username);
		when(userRegistrationService.getAllUsersWithUsername()).thenReturn(username);
		List<UserRegistration> usrnames=username;
		assertThat(tweetAppController.getAllUsers()).isEqualTo(usrnames);
		
	}
	
	//get all tweets
	@Test
	 void getAllTweetsTest() {
		Tweets tweet1=new Tweets();
		tweet1.setTweetId(sequenceGeneratorService.getSequenceNumber(Tweets.SEQUENCE_NAME));
		tweet1.setTitle("God");
		tweet1.setTweet("God is One, Almighty, Wisdom, Love, and more. (This is sometimes called “divine simplicity,” meaning that God and his attributes are perfect, whole, and unified.).");
		
		Tweets tweet2=new Tweets();
		tweet2.setTweetId(sequenceGeneratorService.getSequenceNumber(Tweets.SEQUENCE_NAME));
		tweet2.setTitle("Hii");
		tweet2.setTweet("Hwllo to my first tweet");

		List<Tweets> tweets=new ArrayList<>(Arrays.asList(tweet1,tweet2));
		when(tweetService.getAllTweets()).thenReturn(tweets);
		assertThat(tweetAppController.getAllTweets()).isEqualTo(tweets);
	}
	
	//test for get all tweets of a user
	@Test
	 void getAllTweetsFromUser() {
		Tweets tweet1=new Tweets();
		tweet1.setTweetId(sequenceGeneratorService.getSequenceNumber(Tweets.SEQUENCE_NAME));
		tweet1.setLoginId("Swagatika123");
		tweet1.setTitle("God");
		tweet1.setTweet("God is One, Almighty, Wisdom, Love, and more. (This is sometimes called “divine simplicity,” meaning that God and his attributes are perfect, whole, and unified.).");
		
		Tweets tweet2=new Tweets();
		tweet2.setLoginId("Swagatika123");
		tweet2.setTweetId(sequenceGeneratorService.getSequenceNumber(Tweets.SEQUENCE_NAME));
		tweet2.setTitle("Hii");
		tweet2.setTweet("Hwllo to my first tweet");
		
		List<Tweets> tweets=new ArrayList<>(Arrays.asList(tweet1,tweet2));
		when(tweetService.getAllTweetsByUsername("Swagatika123")).thenReturn(tweets);
		assertThat(tweetAppController.getAllTweetsByUsername("Swagatika123")).isEqualTo(tweets);

	}
	
	//test for search by username partially or fully
	@Test
	 void getAllUsernamesWithSearchedUsernames() {
		UserRegistration userRegistration_1=new UserRegistration("Swagatika123","Swagatika","Mishra","swagatika@123","abc","abc", (long) 798438573);
		UserRegistration userRegistration_2=new UserRegistration("Swati123","swati","Mishra","swati@123","abc","abc", (long) 798448573);
		List<String> presentName=new ArrayList<>(Arrays.asList(userRegistration_1.getLoginId(),userRegistration_2.getLoginId()));
		when(tweetService.findByUsername("Swaga")).thenReturn(presentName);
		assertThat(tweetAppController.searchUserByUsername("Swaga")).isEqualTo(presentName);
	}
	
	//test for delete by tweet id
	@Test
	 void deleteTweetBytweetid_thenreturnsuccess() {
		Tweets tweet1=new Tweets();
		tweet1.setTweetId(sequenceGeneratorService.getSequenceNumber(Tweets.SEQUENCE_NAME));
		tweet1.setLoginId("Swagatika123");
		tweet1.setTitle("God");
		tweet1.setTweet("God is One, Almighty, Wisdom, Love, and more. (This is sometimes called “divine simplicity,” meaning that God and his attributes are perfect, whole, and unified.).");
		tweetRepository.save(tweet1);
		when(tweetRepository.findByTweetId(tweet1.getTweetId())).thenReturn(Optional.of(tweet1));
		when(tweetService.validateUsername(tweet1.getLoginId())).thenReturn(true);
		when(tweetService.deleteTweetsById(tweet1.getTweetId())).thenReturn(true);
		assertThat(tweetAppController.deleteByTweetIdOfUser(tweet1.getLoginId(), tweet1.getTweetId())).isTrue();
	}
	
	//test for update tweet.
	@Test
	 void updateTweetforTweetId() {
		Tweets tweet1=new Tweets();
		tweet1.setTweetId(sequenceGeneratorService.getSequenceNumber(Tweets.SEQUENCE_NAME));
		tweet1.setLoginId("Swagatika123");
		tweet1.setTitle("God decription");
		tweet1.setTweet("Cannot be defined in words");
		when(tweetService.validateUsername("Swagatika123")).thenReturn(true);
		when(tweetService.updateTweetsByTweetId(tweet1.getTweetId(),"Swagatika123",tweet1)).thenReturn("updated");
		ResponseEntity<Object> Response=new ResponseEntity<Object> ("User updated tweet",HttpStatus.ACCEPTED);
		assertThat(tweetAppController.updateTweet("Swagatika123",tweet1.getTweetId(),tweet1)).isEqualTo(Response);
		
	}
	
	//test for post comments
	@Test
	 void testForPostingComments() {
		Comments comment=new Comments();
		comment.setLoginId("Swagatika123");
		comment.setTweetId(1);
		comment.setComment("Hii");
		
		when(userRegistrationService.validateUsername(comment.getLoginId())).thenReturn(true);
		when(commentService.postComment(comment.getLoginId(), 1, comment)).thenReturn("comment posted");
		assertThat(tweetAppController.commentByTweetIdOfUser(comment.getLoginId(), 1, comment)).isTrue();
		
	}
	
	//test for liking the post
	@Test
	void likePostByTweetId() {
		Tweets tweet1=new Tweets();
		tweet1.setTweetId(sequenceGeneratorService.getSequenceNumber(Tweets.SEQUENCE_NAME));
		tweet1.setLoginId("Swati123");
		tweet1.isIslikePressed();
		when(userRegistrationService.validateUsername(tweet1.getLoginId())).thenReturn(true);
		when(tweetService.getLikesByTweetId(tweet1.getTweetId(), tweet1.isIslikePressed())).thenReturn(tweet1.getLikes());
		assertThat(tweetAppController.likeByTweetIdOfUser(tweet1.getLoginId(),tweet1.getTweetId(), tweet1)).isTrue();
	}
	
}
