package com.tweetapp.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweetapp.model.UserRegistration;
import com.tweetapp.repository.UserRegistrationRepository;
import com.tweetapp.service.UserRegistrationService;
import com.tweetapp.validation.TweetAppValidation;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{
	
	@Autowired
	private UserRegistrationRepository userRegistrationRepository;
	
	@Autowired
	private TweetAppValidation tweetAppValidation;
	
//	@Autowired
//	private BCryptPasswordEncoder pwdEncoder;
	
	@Override
	public String addNewRegisteredUser(UserRegistration userRegistration) {
//		userRegistration.setPassword(
//					pwdEncoder.encode(userRegistration.getPassword())
//				);
		userRegistrationRepository.save(userRegistration);
		return "User registered successfully";
	}

	@Override
	public boolean validationFailureCheck(UserRegistration userRegistration) {
		boolean isLoginIdSame=tweetAppValidation.isLoginIdSame(userRegistration.getLoginId());
		boolean isEmailIdSame=tweetAppValidation.isEmailIdSame(userRegistration.getEmail());
		boolean isPasswordSame=tweetAppValidation.isPasswordSame(userRegistration.getPassword(), userRegistration.getConfirmPassword());
		if(isLoginIdSame || isEmailIdSame || !isPasswordSame) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean validateUsername(String username) {
		if(userRegistrationRepository.findById(username).isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean loginUser(String username, String password) {
		if(userRegistrationRepository.findById(username).isPresent() && userRegistrationRepository.findByPassword(password).isPresent()) {
			return true;
		}
		return false;
	}
	


	@Override
	public UserRegistration forgotPassword(String username,UserRegistration newuserRegistration) {
	
		if(userRegistrationRepository.findById(username).isPresent()) {
		
		UserRegistration user= userRegistrationRepository.findById(username).get();
		user.setPassword(newuserRegistration.getPassword());
		user.setConfirmPassword(newuserRegistration.getPassword());
		UserRegistration updatedUser=userRegistrationRepository.save(user);
		return new UserRegistration(updatedUser.getLoginId(),updatedUser.getFirstName(),updatedUser.getLastName(),
				updatedUser.getEmail(),updatedUser.getPassword(),updatedUser.getConfirmPassword(),updatedUser.getContactNumber());
			   
		}
		return null;
		
	}

	public List<UserRegistration> getAllUsersWithUsername(){
	return userRegistrationRepository.findAllLoginIdsDetails();
			    
	}
	
	public List<String> getAllUsersname(){
		return userRegistrationRepository.findAllLoginIds();
	}

	
   

	
}
