package com.tweetapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.Tweets;

@Repository
public interface TweetRepository extends MongoRepository<Tweets,String> {

	@Query(value="{}",fields="{'loginId':1,'title':1,'tweet':1,'creationDate':1,'likes':1,'comments':1}")
	List<Tweets> getAllTweetsByOrderByTweetIdDesc(Sort sort); 
	
	
	@Query("{'loginId' : ?0}")
    List<Tweets> findByloginId(String loginId,Sort sort);
	
	Optional<Tweets> findByTweetId(int id);
	
	@Query("{'tweetId':?0}")
	Tweets getByTweetId(int id);
	
	Tweets getTweetsByTweetId(int id);
	
	void deleteByTweetId(int id);
}
