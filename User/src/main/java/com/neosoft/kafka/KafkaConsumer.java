package com.neosoft.kafka;

import com.neosoft.model.Activity;
import com.neosoft.model.User;
import com.neosoft.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

@Autowired
    private ActivityService activityService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message){
        log.info(String.format("Message received -> %s", message));
        Activity activity = new Activity();
        activity.setActivityInfo(message);
        activityService.saveActivity(activity);
    }
}