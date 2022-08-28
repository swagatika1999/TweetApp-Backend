package com.tweetapp.model;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Valid
@AllArgsConstructor
@NoArgsConstructor
@Document("Tweet")
public class Tweets {
	

	@Transient
	public static final String SEQUENCE_NAME="tweet_sequence";
	
	@Id
	private int tweetId;
	
	private String loginId;
	
	@NotNull(message="Title cannot be null")
	@NotEmpty(message="Title cannot be empty")
	@Size(min=1,max=50)
	private String title;
	
	@NotNull(message="Tweet cannot be null")
	@NotEmpty(message="Tweet cannot be empty")
	@Size(min=1,max=144)
	private String tweet;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date creationDate=new Date();
	
	
    private List<Comments> comments;
	
    private int likes;
    
    private boolean islikePressed;
    

}