package com.neosoft.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import cm.neosoft.model.Topic;



@Service
public class TopicService {

	private List<Topic> topics=new ArrayList(Arrays.asList(
			
			new Topic(1,"Java","Console appliaction"),
			new Topic(2,"Oracle","Backend Tool"),
			new Topic(3,"Java","ORM Tool")	
			));
	
	
	public List<Topic> listTopic()
	{
		return topics;
	}
}