package com.tweetapp.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tweetapp.model.Comments;
import com.tweetapp.repository.CommentsRepository;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.service.CommentService;

@Service
public class CommentsServiceImpl implements CommentService{
	
	@Autowired
	private TweetRepository tweetRepository;
	
	@Autowired
	private CommentsRepository commentsRepository;

	@Override
	public String postComment(String username, int tweetId, Comments comment) {
		if(tweetRepository.findByTweetId(tweetId).isPresent()) {
			comment.setLoginId(username);
			comment.setTweetId(tweetId);
			comment.setCommentPostedDate(new Date());
			commentsRepository.save(comment);
			
		}
			return "comment not  posted";
		
	}

	@Override
	public List<Comments> getAllCommentsByTweetId(int tweetid) {
		if(tweetRepository.findByTweetId(tweetid).isPresent()) {
			return commentsRepository.findCommentsBytweetId(tweetid,Sort.by(Sort.Direction.DESC, "commentPostedDate"));
		}
		
		return null;
	}
	
	

	
}
