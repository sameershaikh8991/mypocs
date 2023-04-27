package com.neosoft.service;

import com.neosoft.model.Activity;
import com.neosoft.repository.ActivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepo activityRepo;

    public void  saveActivity(Activity activity){
        activityRepo.save(activity);
    }


}
