package com.tweetapp.model;

import javax.validation.Valid;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Valid
@AllArgsConstructor
@NoArgsConstructor
@Document("TweetStatus")
public class TweetPostingStatus {

	private Tweets tweet;
	private String statusOfPosting;
	private String message;
	
}
