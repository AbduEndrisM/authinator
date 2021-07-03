package com.jeba.authinator.controller;

import com.jeba.authinator.domain.OTPVerifyPayload;
import com.jeba.authinator.domain.PhoneNumber;
import com.jeba.authinator.service.IOtpVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/otp/")
public class OtpVerificationController {

    private final IOtpVerificationService otpVerificationService;

    @Autowired
    public OtpVerificationController(IOtpVerificationService otpVerificationService) {
        this.otpVerificationService = otpVerificationService;
    }


    //https://www.youtube.com/watch?v=Ta3FgS4HEwY
    @PostMapping("send")
    public ResponseEntity<Object> sendOtp(@RequestBody @Valid PhoneNumber phoneNumber){
        System.out.println("LOG:  OtpVerificationController sendOtp()  phone number "+ phoneNumber.getPhoneNumber());
        return otpVerificationService.sendOtp(phoneNumber);
    }

    @PutMapping("verify")
    public ResponseEntity<Object> verifyOtp(@RequestBody @Valid OTPVerifyPayload otpVerifyPayload){
        System.out.println("LOG:  OtpVerificationController verifyOtp()  phone number "+
                otpVerifyPayload.getPhoneNumber().getPhoneNumber()
                + "  otpCode  " + otpVerifyPayload.getOtpCode());

        return otpVerificationService.verifyOtp(otpVerifyPayload);
    }




}
