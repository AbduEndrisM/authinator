package com.jeba.authinator.service;

import com.jeba.authinator.adapter.ITwilioOtpAdapter;
import com.jeba.authinator.domain.payload.OTPVerifyPayload;
import com.jeba.authinator.domain.PhoneNumber;
import com.jeba.authinator.domain.entity.OtpEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpService implements IOtpService {

    private final ITwilioOtpAdapter otpVerificationAdapter;


    @Override
    public OtpEntity sendOtp(PhoneNumber phoneNumber) {

        if (isPhoneNumberValid(phoneNumber.getPhoneNumber())) {
            return otpVerificationAdapter.sendOtp(phoneNumber);
        } else {
            throw new IllegalArgumentException("Invalid Number");
        }

    }


    @Override
    public OtpEntity verifyOtp(OTPVerifyPayload otpVerifyPayload) {


        System.out.println("LOG:  OtpVerificationService verifyOtp()  phoneNumber " + otpVerifyPayload.getPhoneNumber()
                + "  otpCode" + otpVerifyPayload.getOtpCode());

        return otpVerificationAdapter.verifyOtp(otpVerifyPayload);

    }


    @Override
    public OtpEntity reSendOtp(PhoneNumber phoneNumber) {
        System.out.println("LOG:  OtpVerificationService reSendOtp()  phone number  " + phoneNumber.getPhoneNumber());

        return otpVerificationAdapter.reSendOtp(phoneNumber);

    }


    private boolean isPhoneNumberValid(String phoneNumber) {
        return true; //TODO: implement google's phone number validator or just count # of digits = 9
    }
}