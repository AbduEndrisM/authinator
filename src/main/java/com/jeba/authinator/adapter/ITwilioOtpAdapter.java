package com.jeba.authinator.adapter;

import com.jeba.authinator.domain.PhoneNumber;
import com.jeba.authinator.domain.entity.OtpEntity;
import com.jeba.authinator.domain.payload.OTPVerifyPayload;
import org.springframework.http.ResponseEntity;

public interface ITwilioOtpAdapter {
    OtpEntity sendOtp(PhoneNumber phoneNumber);

    OtpEntity verifyOtp(OTPVerifyPayload otpVerifyPayload);

    OtpEntity reSendOtp(PhoneNumber phoneNumber);
}
