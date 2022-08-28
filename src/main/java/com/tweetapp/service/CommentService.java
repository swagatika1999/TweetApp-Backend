package com.tweetapp.service;

import java.util.List;

import com.tweetapp.model.Comments;

public interface CommentService {

	public String postComment(String username,int tweetId,Comments comment);
	
	public List<Comments> getAllCommentsByTweetId(int tweetid);
}
