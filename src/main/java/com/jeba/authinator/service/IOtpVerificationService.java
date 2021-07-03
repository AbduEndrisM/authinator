package com.jeba.authinator.service;

import com.jeba.authinator.domain.OTPVerifyPayload;
import com.jeba.authinator.domain.PhoneNumber;
import org.springframework.http.ResponseEntity;

public interface IOtpVerificationService {

    ResponseEntity<Object> sendOtp(PhoneNumber phoneNumber);

    ResponseEntity<Object> verifyOtp(OTPVerifyPayload otpVerifyPayload);
}
