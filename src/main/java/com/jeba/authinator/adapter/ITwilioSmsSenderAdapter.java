package com.jeba.authinator.adapter;

import com.jeba.authinator.domain.Sms;

public interface ITwilioSmsSenderAdapter {
     void sendSms(Sms sms);
}
