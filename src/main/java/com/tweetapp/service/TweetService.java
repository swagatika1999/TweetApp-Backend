package com.tweetapp.service;

import java.util.List;

import com.tweetapp.model.Comments;
import com.tweetapp.model.Tweets;

public interface TweetService {

	public boolean postTweets(String username,Tweets tweet);
	
	public boolean validateUsername(String username);
	
	public List<Tweets> getAllTweets();
	
	public List<Tweets> getAllTweetsByUsername(String username);
	
	public List<String> findByUsername(String username);
	
	public boolean deleteTweetsById(int tId);
	
	public String updateTweetsByTweetId(int tweetid,String username,Tweets tweet);
	
	//public List<Comments> showComments(int id,List<Comments> comment);
	
	public int getLikesByTweetId(int tweetid,boolean like);
	
	public Tweets getATweet(int id);
}
