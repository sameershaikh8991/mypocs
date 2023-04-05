package com.sms.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
@Service
public class SMSServiceTwilio implements SMSService{
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC679051c1aabbbf185a34f2eaaab9d7ad";
    public static final String AUTH_TOKEN = "8d454c2d99fcdc0bd187e74f19a1b5cd";

    @Override
    public Message sendSMS() {

        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+919762167299"),new PhoneNumber("+15855360891"),"hello sameer").create();
        return message;
    }
}
