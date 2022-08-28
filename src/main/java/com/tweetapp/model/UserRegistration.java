package com.tweetapp.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Valid
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("Registration")
public class UserRegistration {
	
	
	@Id
	@NotBlank(message="Login id cannot be empty")
	private String loginId;
	
	@NotBlank(message="First Name cannot be empty")
	private String firstName;
	
	@NotBlank(message="Last Name cannot be empty")
	private String lastName;
	
	@NotBlank(message="Login id cannot be empty")
	private String email;
	
	@NotBlank(message="Password cannot be empty")
	private String password;
	
	@NotBlank(message="confirm password cannot be empty")
	private String confirmPassword;
	
	@NotNull(message="Contact Number cannot be empty")
	private Long contactNumber;
	
	
	
	
}
