package com.tweetapp.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tweetapp.model.UserRegistration;
import com.tweetapp.repository.UserRegistrationRepository;

@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceImplTest {

	@Mock
	private UserRegistrationRepository userRegistrationRepository;
	
	@InjectMocks
	private UserRegistrationServiceImpl userRegistrationService;
	
	private UserRegistration userRegistration;

	@BeforeEach
	public void setup() {
		userRegistration=UserRegistration.builder()
						.loginId("Sneha423")
						.firstName("Sneha")
						.lastName("Mishra")
						.email("sneha@123")
						.password("hello")
						.confirmPassword("hello")
						.contactNumber((long) 797456782)
						.build();

	}
	
	@DisplayName("test for save user")
	@Test
	void givenUser_whenRegisterAndSave_thenReturnStringMessage() {
		String userRegistrationsave=userRegistrationService.addNewRegisteredUser(userRegistration);
		String successfulResgistration="User registered successfully";
		assertEquals(userRegistrationsave,successfulResgistration);
	}
	
	
	@DisplayName("test for negative scenario can't login")
	@Test
	void givenUser_enteredLoginAndPasswordWrong_thenReturnboolValue() {
		boolean userLoginPassword=userRegistrationService.loginUser("Sneha423", "hel");
		boolean cantLogin=false;
		assertEquals(userLoginPassword,cantLogin);
	}
	
	
//	@DisplayName("test for forgot password negative scenario")
//	@Test
//	void givenUserwrongid_forgotPassword_returnString() {
//		String forgotPasswordUsername=userRegistrationService.forgotPassword("Sneha43", "hel");
//		String returnedStringUnSuccessful="User with username not found!";
//		assertEquals(forgotPasswordUsername,returnedStringUnSuccessful);
//	}
}
