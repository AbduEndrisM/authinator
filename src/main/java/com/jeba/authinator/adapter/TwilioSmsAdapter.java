package com.jeba.authinator.adapter;

import com.jeba.authinator.config.TwilioConfiguration;
import com.jeba.authinator.domain.payload.SmsPayload;
import com.jeba.authinator.domain.entity.SmsEntity;
import com.jeba.authinator.repository.ISmsRepository;
import com.twilio.exception.ApiException;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TwilioSmsAdapter implements ITwilioSmsAdapter {

    private final TwilioConfiguration twilioConfiguration;
    private final ISmsRepository smsSenderRepository;


    @Override
    public SmsEntity sendSms(SmsPayload smsPayload) throws Throwable {
        String phoneNumber = smsPayload.getTo();


        try {


            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            PhoneNumber to = new PhoneNumber(smsPayload.getTo());
//            String message = sms.getMessage();
//            Message messageCreator = Message.creator(to, from, message).create();

//            MessageCreator messageCreator = new MessageCreator(to, from, message);
            System.out.println("Sending sms " + smsPayload.getMessage() + " from " + from + ": to  " + to);
//              Message.creator(new PhoneNumber(sms.getPhoneNumber()), new PhoneNumber("+14256246708"), "msg"); //TODO: remove
//            messageCreator.create();
//            System.out.println(messageCreator.getSid());
//            https://www.twilio.com/docs/sms/quickstart/java

        } catch (ApiException e) {
            throw new Throwable(e.getMessage());
        }

        SmsEntity smsEntity = new SmsEntity();

        smsEntity.setFrom(new PhoneNumber(twilioConfiguration.getTrialNumber()).toString());
        smsEntity.setTo(smsPayload.getTo());
        smsEntity.setMessage(smsPayload.getMessage());
        smsEntity.setSentTime(Instant.now());
        smsEntity.setDescription((smsPayload.getDescription() == null ? "N/A" : smsPayload.getDescription()));

        SmsEntity smsEntityResponse = smsSenderRepository.save(smsEntity);
        System.out.println(smsEntityResponse);
        return smsEntityResponse;

    }


}
