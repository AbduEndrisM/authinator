package com.jeba.authinator.service;

import com.jeba.authinator.adapter.ITwilioSmsAdapter;
import com.jeba.authinator.domain.entity.SmsEntity;
import com.jeba.authinator.domain.payload.SmsPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService implements ISmsService {

    private final ITwilioSmsAdapter twilioSmsSenderAdapter;


    @Override
    public SmsEntity sendSms(SmsPayload smsPayload) throws Throwable {

        if (isPhoneNumberValid(smsPayload.getTo())) {
            return twilioSmsSenderAdapter.sendSms(smsPayload);
        } else {
            throw new IllegalArgumentException("Invalid Number");
        }

    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return true; //TODO: implement google's phone number validator or just count # of digits = 9
    }
}

