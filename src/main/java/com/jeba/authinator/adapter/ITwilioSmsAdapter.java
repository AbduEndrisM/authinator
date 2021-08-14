package com.jeba.authinator.adapter;

import com.jeba.authinator.domain.entity.SmsEntity;
import com.jeba.authinator.domain.payload.SmsPayload;

public interface ITwilioSmsAdapter {
    SmsEntity sendSms(SmsPayload smsPayload) throws Throwable;
}
