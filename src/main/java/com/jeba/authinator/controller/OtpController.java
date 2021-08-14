package com.jeba.authinator.controller;

import com.jeba.authinator.domain.payload.OTPVerifyPayload;
import com.jeba.authinator.domain.PhoneNumber;
import com.jeba.authinator.domain.entity.OtpEntity;
import com.jeba.authinator.service.IOtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/otp/")
public class OtpController {

    private final IOtpService otpVerificationService;


    //https://www.youtube.com/watch?v=Ta3FgS4HEwY
    @PostMapping("send")
    public ResponseEntity<OtpEntity> sendOtp(@RequestBody PhoneNumber phoneNumber) {
        System.out.println("LOG:  OtpVerificationController sendOtp()  phoneNumber " + phoneNumber.getPhoneNumber());

        OtpEntity entity = otpVerificationService.sendOtp(phoneNumber);
        return ResponseEntity.accepted().body(entity);
    }


    @PutMapping("verify")
    public ResponseEntity<OtpEntity> verifyOtp(@RequestBody OTPVerifyPayload otpVerifyPayload) {
        System.out.println("LOG:  OtpVerificationController verifyOtp()  phone number " +
                otpVerifyPayload.getPhoneNumber()
                + "  otpCode  " + otpVerifyPayload.getOtpCode());

        OtpEntity otpEntity = otpVerificationService.verifyOtp(otpVerifyPayload);
        return ResponseEntity.ok().body(otpEntity);
    }


    @PostMapping("resend")
    public ResponseEntity<OtpEntity> reSendOtp(@RequestBody PhoneNumber phoneNumber) {
        System.out.println("LOG:  OtpVerificationController reSendOtp()  phoneNumber " + phoneNumber.getPhoneNumber());
        OtpEntity otpEntity = otpVerificationService.reSendOtp(phoneNumber);
        return ResponseEntity.ok().body(otpEntity);

    }


}
