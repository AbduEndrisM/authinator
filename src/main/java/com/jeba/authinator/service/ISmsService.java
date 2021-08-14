package com.jeba.authinator.service;

import com.jeba.authinator.domain.entity.SmsEntity;
import com.jeba.authinator.domain.payload.SmsPayload;

public interface ISmsService {
    SmsEntity sendSms(SmsPayload smsPayload) throws Throwable;
}
