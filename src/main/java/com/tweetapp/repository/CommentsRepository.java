package com.tweetapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.Comments;


@Repository
public interface CommentsRepository extends MongoRepository<Comments,String>{

	Optional<Comments> findByTweetId(int tweetId);
	
	@Query("{'tweetId' : ?0}")
    List<Comments> findCommentsBytweetId(int tweetId,Sort sort);
	
	
}
