package com.jeba.authinator.service;

import com.jeba.authinator.adapter.ITwilioSmsSenderAdapter;
import com.jeba.authinator.domain.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsSenderService implements ISmsSenderService {

    private final ITwilioSmsSenderAdapter twilioSmsSender;

    @Autowired
    public SmsSenderService(ITwilioSmsSenderAdapter twilioSmsSender) {
        this.twilioSmsSender = twilioSmsSender;
    }

    @Override
    public void sendSms(Sms sms) {
        twilioSmsSender.sendSms(sms);
    }
}
