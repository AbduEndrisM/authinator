package com.jeba.authinator.service;

import com.jeba.authinator.domain.payload.OTPVerifyPayload;
import com.jeba.authinator.domain.PhoneNumber;
import com.jeba.authinator.domain.entity.OtpEntity;

public interface IOtpService {

    OtpEntity sendOtp(PhoneNumber phoneNumber);

    OtpEntity verifyOtp(OTPVerifyPayload otpVerifyPayload);

    OtpEntity reSendOtp(PhoneNumber phoneNumber);

}
