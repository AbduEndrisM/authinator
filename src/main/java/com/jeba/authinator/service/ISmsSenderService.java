package com.jeba.authinator.service;

import com.jeba.authinator.domain.Sms;

public interface ISmsSenderService {
     void sendSms(Sms sms);
}
