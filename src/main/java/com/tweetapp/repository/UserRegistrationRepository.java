package com.tweetapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.UserRegistration;

@Repository
public interface UserRegistrationRepository extends MongoRepository<UserRegistration, String>{

	Optional<UserRegistration> findById(String loginId);
	Optional<UserRegistration> findByEmail(String emailId);
	Optional<UserRegistration> findByPassword(String password);
	
	@Query(value="{}",fields="{ _id:1,firstName:1,lastName:1}")
	List<UserRegistration> findAllLoginIdsDetails();
	
	@Query(value="{}",fields="{_id:1}")
	List<String> findAllLoginIds();
	
	
	UserRegistration findByLoginId(String username);
	
	
	
	
}
