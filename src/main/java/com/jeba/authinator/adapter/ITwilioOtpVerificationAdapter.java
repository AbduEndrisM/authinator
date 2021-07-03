package com.jeba.authinator.adapter;

import com.jeba.authinator.domain.OTPSystem;
import com.jeba.authinator.domain.PhoneNumber;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ITwilioOtpVerificationAdapter {
    ResponseEntity<Object> sendOtp(PhoneNumber phoneNumber, Map<PhoneNumber, OTPSystem> otpData);
}
