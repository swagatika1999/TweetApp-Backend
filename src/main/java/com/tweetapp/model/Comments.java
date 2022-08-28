package com.tweetapp.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("comments")
public class Comments {

	@Id
	private String commentId;
	private String loginId;
	private int tweetId;
	private String comment;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date commentPostedDate;

	
	
	
}
