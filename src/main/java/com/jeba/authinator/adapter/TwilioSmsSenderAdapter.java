package com.jeba.authinator.adapter;

import com.jeba.authinator.config.TwilioConfiguration;
import com.jeba.authinator.domain.Sms;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsSenderAdapter implements ITwilioSmsSenderAdapter {

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSenderAdapter(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSms(Sms sms) {
        PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
        PhoneNumber to = new PhoneNumber(sms.getPhoneNumber());
        if (isPhoneNumberValid(to)) {
            String message = sms.getMessage();
            MessageCreator messageCreator = new MessageCreator(to, from, message);
            System.out.println("Sending sms "+ sms.getMessage()+ " from "+ from+ ": to  "+  to);
            //  Message.creator(new PhoneNumber(sms.getPhoneNumber()), new PhoneNumber("+14256246708"), "msg"); //TODO: remove
            messageCreator.create();
        }
        else {
            throw new IllegalArgumentException("Invalid Number");
        }
    }

    private static boolean isPhoneNumberValid(PhoneNumber to) {
        return true; //TODO: implement google's phone number validator or just count # of digits = 9
    }

}
