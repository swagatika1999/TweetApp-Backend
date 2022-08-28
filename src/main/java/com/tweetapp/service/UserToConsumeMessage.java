package com.tweetapp.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import com.tweetapp.config.MessagingConfig;
import com.tweetapp.model.TweetPostingStatus;


@Service
@Log4j2
public class UserToConsumeMessage {
	
	@RabbitListener(queues = MessagingConfig.QUEUE1)
	public void consumeMessageFromQueue(TweetPostingStatus tweetpostingstatus) {
		log.info("Message received from queue : {}", tweetpostingstatus );
	}

}
