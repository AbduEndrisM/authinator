package com.jeba.authinator.adapter;

import com.jeba.authinator.config.TwilioConfiguration;
import com.jeba.authinator.domain.OTPSystem;
import com.jeba.authinator.domain.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TwilioOtpVerificationAdapter implements ITwilioOtpVerificationAdapter{


    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioOtpVerificationAdapter(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public ResponseEntity<Object> sendOtp(PhoneNumber phoneNumber, Map<com.jeba.authinator.domain.PhoneNumber, OTPSystem> otpData) {

        com.twilio.type.PhoneNumber from = new com.twilio.type.PhoneNumber(twilioConfiguration.getTrialNumber());
        com.twilio.type.PhoneNumber to = new com.twilio.type.PhoneNumber(phoneNumber.getPhoneNumber().toString());
        String otpCode = String.valueOf(otpData.get(phoneNumber).getOtpCode());

        System.out.println("TO:  " + to.toString()); //TODO
        System.out.println("From:  " +from.toString()); //TODO
        System.out.println("OTP Code:  "+ otpCode); //TODO

//        MessageCreator messageCreator = new MessageCreator(to, from,"Your Jeba verification code is: "+ otpCode);
//        messageCreator.create();

        return new ResponseEntity<>("OTP Code has been sent successfully", HttpStatus.OK);
    }


}
