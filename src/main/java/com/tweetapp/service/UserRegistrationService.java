package com.tweetapp.service;

import java.util.List;

import com.tweetapp.model.UserRegistration;


public interface UserRegistrationService {
	
	public String addNewRegisteredUser(UserRegistration userRegistration);
	
	public boolean validationFailureCheck(UserRegistration userRegistration);
	
	public boolean loginUser(String username,String password);
	
	public UserRegistration forgotPassword(String username,UserRegistration userRegistration);
	
	//public boolean findbyUsername(String username);
	
	public List<UserRegistration> getAllUsersWithUsername();
	
	public List<String> getAllUsersname();
	
	public boolean validateUsername(String username);
}
