package com.tweetapp.service;


import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.tweetapp.model.DbSequence;

@Service
public class SequenceGeneratorService {

	
	@Autowired
	private MongoOperations mongoOperation;
	
	public int getSequenceNumber(String sequenceNumber) {
		//get seq number
		Query query=new Query(Criteria.where("id").is(sequenceNumber));
		//update the seq no.
		Update update=new Update().inc("seq", 1);
		//modify in document
		DbSequence counter=mongoOperation
						   .findAndModify(query, update,
								   options().returnNew(true).upsert(true)
								   , DbSequence.class);
		
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}
}
