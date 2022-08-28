package com.tweetapp.serviceimpl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tweetapp.model.Comments;
import com.tweetapp.model.Tweets;
import com.tweetapp.repository.CommentsRepository;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UserRegistrationRepository;
import com.tweetapp.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService{

	@Autowired 
	private TweetRepository tweetRepository;
	
	@Autowired
	private UserRegistrationRepository userRegistrationRepository;
	
	@Autowired
	private CommentsRepository commentsRepository;

	@Override
	public boolean postTweets(String username,Tweets tweet) {
		tweet.setLoginId(username);
		tweetRepository.save(tweet);
		return true;
	} 
	
	@Override
	public boolean validateUsername(String username) {
		if(userRegistrationRepository.findById(username).isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public List<Tweets> getAllTweets() {
		return tweetRepository.getAllTweetsByOrderByTweetIdDesc(Sort.by(Sort.Direction.DESC, "creationDate"));
	}

	@Override
	public List<Tweets> getAllTweetsByUsername(String username) {
		return tweetRepository.findByloginId(username,Sort.by(Sort.Direction.DESC, "creationDate"));
		
	}

	@Override
	public List<String> findByUsername(String username) {
		String usrname=username.toLowerCase();
		List<String> usernameList=new ArrayList<String>();
		List<String> usernameExistingList=userRegistrationRepository.findAllLoginIds();
		if(validateUsername(usrname)) {
			usernameList.add(usrname);
		}
		
		else if(!(validateUsername(usrname))) {
			for(String uname:usernameExistingList) {
				boolean isNamePresent=(uname.toLowerCase().contains(usrname));
				if(isNamePresent) {
					usernameList.add(uname);
				}
			}
		}
		return usernameList;
	}

	@Override
	public boolean deleteTweetsById(int tid) {
		
		if(tweetRepository.findByTweetId(tid).isPresent()) {
			tweetRepository.deleteByTweetId(tid);
			return true;
		}
		return false;
	}

	@Override
	public String updateTweetsByTweetId(int tweetid,String username, Tweets tweet) {
		if(tweetRepository.findByTweetId(tweetid).isPresent()) {
			tweet.setTweetId(tweetid);
			tweet.setLoginId(username);
			tweetRepository.save(tweet);
			return "updated";
		}
		
		return "Not updated";
	}

	@Override
	public int getLikesByTweetId(int tweetid, boolean like) {
		Tweets tweet=tweetRepository.getByTweetId(tweetid);
		int liketweet=tweet.getLikes();
		
		tweet.setIslikePressed(like);
		
			
			liketweet++;
			tweet.setLikes(liketweet);			
			tweetRepository.save(tweet);
			
		
		
		return liketweet;
	}

	@Override
	public Tweets getATweet(int id) {
		if(tweetRepository.findByTweetId(id).isPresent() ) {
			return tweetRepository.getTweetsByTweetId(id);
			
		}
		return null;
	}

//	@Override
//	public List<Comments> showComments(int id,List<Comments> comment) {
//		Tweets tweet=new Tweets();
//		if(tweetRepository.findByTweetId(id).isPresent()) {
//			if(commentsRepository.findByTweetId(id).isPresent()) {
//				tweet.setComments(comment);
//				return comment;
//			}
//		}
//		return null;
//	}
	
	

	

		
	
}
