package com.tweetapp.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.tweetapp.repository.UserRegistrationRepository;

@Service
@Configurable
public class TweetAppValidation {

	@Autowired
	public UserRegistrationRepository userRegistrationRepository;
	
	public boolean isLoginIdSame(String userLoginId) {
		Boolean isLoginidPresent=userRegistrationRepository.findById(userLoginId).isPresent();
		if(isLoginidPresent) {
			return true;
		}
		return false;
	}
	
	public boolean isEmailIdSame(String emailId) {
		Boolean isEmailidPresent=userRegistrationRepository.findByEmail(emailId).isPresent();
		if(isEmailidPresent) {
			return true;
		}
		return false;
	}
	
	public boolean isPasswordSame(String password,String confirmPassword) {
		if(password.equals(confirmPassword)) {
			return true;
		}
		return false;
	}
	
}
