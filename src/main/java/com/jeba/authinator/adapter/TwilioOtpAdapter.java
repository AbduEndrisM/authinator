package com.jeba.authinator.adapter;

import com.jeba.authinator.config.TwilioConfiguration;
import com.jeba.authinator.domain.PhoneNumber;
import com.jeba.authinator.domain.entity.OtpEntity;
import com.jeba.authinator.domain.payload.OTPVerifyPayload;
import com.jeba.authinator.domain.payload.OtpRequestPayload;
import com.jeba.authinator.repository.IOtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class TwilioOtpAdapter implements ITwilioOtpAdapter {


    private final TwilioConfiguration twilioConfiguration;
    private final IOtpRepository otpVerificationRepository;

    @Override
    public OtpEntity sendOtp(com.jeba.authinator.domain.PhoneNumber phoneNumber) {


        AtomicBoolean phoneNumberExist = this.validateIfPhoneNumberExist(phoneNumber);


        if (phoneNumberExist.get() == false) {
            return sendingOtp(phoneNumber);
        }

        throw new IllegalArgumentException(" Sorry, OTP Code has been sent");


    }

    private OtpEntity sendingOtp(PhoneNumber phoneNumber) {

        OtpRequestPayload otpRequestPayload = setOtpInfo(phoneNumber.getPhoneNumber());

        com.twilio.type.PhoneNumber from = new com.twilio.type.PhoneNumber(twilioConfiguration.getTrialNumber());
        com.twilio.type.PhoneNumber to = new com.twilio.type.PhoneNumber(phoneNumber.getPhoneNumber());

        String otpCode = String.valueOf(otpRequestPayload.getOtpCode());

        System.out.println("LOG:  TwilioOtpAdapter reSendOtp()  " +
                "To: " + to.getEndpoint() + "From: " + from.getEndpoint() + " Otp Code: " + otpCode);


        try {

//        Enable this for real sms experience
//        MessageCreator messageCreator = new MessageCreator(to, from,"Hi Sebliye: your verification code is: "+ otpCode);
//        messageCreator.create();
            System.out.println("LOG:  TwilioOtpAdapter: Twilio has successfully sent a message");

            OtpEntity otpEntity = mapToOTPEntity(otpRequestPayload);
            return otpVerificationRepository.save(otpEntity);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Twilio cant send sms");
        }

    }

    @Override
    public OtpEntity verifyOtp(OTPVerifyPayload otpVerifyPayload) {

        System.out.println("LOG:  TwilioOtpAdapter verifyOtp() Payload Info " +
                "PhoneNumber: " + otpVerifyPayload.getPhoneNumber() + " Otp Code: " + otpVerifyPayload.getOtpCode());

//        List<OtpEntity> otpEntity = otpVerificationRepository.findAllByToOrderByExpiryTimeDesc(otpVerifyPayload.getPhoneNumber());

//        OtpEntity entity = otpEntity.get(otpEntity.size() - 1);
        Optional<OtpEntity> entityX = otpVerificationRepository.findFirstByToOrderByExpiryTimeDesc(otpVerifyPayload.getPhoneNumber());


        entityX.ifPresentOrElse(entity -> {


            System.out.println("LOG:  TwilioOtpAdapter verifyOtp() Entity Info " +
                    "PhoneNumber: " + entity.getTo() + " Otp Code: " + entity.getOtpCode() +
                    "Expiration Time: " + entity.getExpiryTime());

            if (entity.getExpiryTime().isBefore(Instant.now())) {
                throw new IllegalArgumentException("OTP Code has been expired");
            }
            if (entity.getOtpCode() != otpVerifyPayload.getOtpCode())
                throw new IllegalArgumentException(" OTP Code is not correct");


        }, () -> {
            throw new IllegalStateException("No Number regitered");
        });
        return entityX.get();
    }

    @Override
    public OtpEntity reSendOtp(com.jeba.authinator.domain.PhoneNumber phoneNumber) {
        System.out.println("LOG:  TwilioOtpAdapter reSendOtp()  " +
                "To: " + phoneNumber.getPhoneNumber());
        AtomicBoolean hasBeenSent = validateIfCodeHasBeenAlreadySent(phoneNumber);

        if (hasBeenSent.equals(false)) {
            return sendingOtp(phoneNumber);
        }

        throw new IllegalArgumentException(" OTP Code has been sent");

    }

    private AtomicBoolean validateIfCodeHasBeenAlreadySent(com.jeba.authinator.domain.PhoneNumber phoneNumber) {


        //check number exist
        //check otp code is not expired
        AtomicBoolean phoneNumberExist = new AtomicBoolean(false);
        Optional<OtpEntity> otpEntity = otpVerificationRepository.findFirstByToOrderByExpiryTimeDesc(phoneNumber.getPhoneNumber());

        otpEntity.ifPresentOrElse(x -> {

            if (x.getExpiryTime().isAfter(Instant.now())) {
                throw new UnsupportedOperationException("Wait for a moment please, Code has already been sent!");
            }
            if (x.getExpiryTime().isBefore(Instant.now())) {
                phoneNumberExist.set(true);
            }
        }, () -> {
            phoneNumberExist.set(true);
        });

        return phoneNumberExist;
    }


    private AtomicBoolean validateIfPhoneNumberExist(com.jeba.authinator.domain.PhoneNumber phoneNumber) {


        AtomicBoolean phoneNumberExist = new AtomicBoolean();
        Optional<OtpEntity> otpEntity = otpVerificationRepository.findFirstByToOrderByExpiryTimeDesc(phoneNumber.getPhoneNumber());

        otpEntity.ifPresentOrElse(x -> {

            ;
            phoneNumberExist.set(validateIfCodeHasBeenAlreadySent(phoneNumber).get());
        }, () -> {
            phoneNumberExist.set(false);
        });


        return phoneNumberExist;
    }


    private OtpEntity mapToOTPEntity(OtpRequestPayload otpRequestPayload) {
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setTo(otpRequestPayload.getTo());
        otpEntity.setOtpCode(otpRequestPayload.getOtpCode());
        otpEntity.setFrom(otpRequestPayload.getFrom());
        otpEntity.setExpiryTime(Instant.now().plusSeconds(120));
        return otpEntity;
    }


    private OtpRequestPayload setOtpInfo(String phoneNumber) {
        System.out.println("LOG:  OtpVerificationService setOtpInfo()  phone number " + phoneNumber);

        Random random = new Random();
        int low = 1001; //TODO: make it a 6 digit number - just add 2 zeros and 2 nines
        int high = 9999;
        int otpCode = random.nextInt(high - low) + low;


        OtpRequestPayload otpRequestPayload = new OtpRequestPayload();
        otpRequestPayload.setOtpCode(otpCode);
        otpRequestPayload.setTo(phoneNumber);

        System.out.println("LOG:  OtpVerificationService OTP System - Info " + otpRequestPayload);

        return otpRequestPayload;
    }


}
