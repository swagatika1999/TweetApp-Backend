package com.tweetapp.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweetapp.model.Comments;
import com.tweetapp.model.Tweets;
import com.tweetapp.repository.CommentsRepository;
import com.tweetapp.repository.TweetRepository;

@ExtendWith(MockitoExtension.class)
class CommentsServiceImplTest {

	@Mock
	private TweetRepository tweetRepository;
	
	@Mock
	private CommentsRepository commentsRepository;
	
	@InjectMocks
	private CommentsServiceImpl commentsService;
	
	private Tweets tweet;
	
	private Comments comment;
	
	@BeforeEach
	public void setup() {
			tweet=Tweets.builder()
						.tweetId(1)
						.loginId("Sneha423")
						.title("My first post")
						.tweet("Writing it for the first time")
						.creationDate(new Date())
						.build();

	}

	@BeforeEach
	public void setup1() {
		comment=Comments.builder()
						.tweetId(1)
						.loginId("Swagatika123")
						.comment("Hii")
						.commentPostedDate(new Date())
						.build();

	}
	
	@DisplayName("test for commenting on users tweet")
	@Test
	void givenUserPostComment_returnString() {
		String savedComment=commentsService.postComment("Swagatika123", 1, comment);
		String isCommentPosted="comment not  posted";
		assertEquals(savedComment,isCommentPosted);
	}
}
