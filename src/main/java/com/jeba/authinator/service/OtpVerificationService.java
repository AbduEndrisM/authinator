package com.jeba.authinator.service;

import com.jeba.authinator.adapter.ITwilioOtpVerificationAdapter;
import com.jeba.authinator.domain.OTPSystem;
import com.jeba.authinator.domain.OTPVerifyPayload;
import com.jeba.authinator.domain.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpVerificationService implements IOtpVerificationService {

    private final ITwilioOtpVerificationAdapter otpVerificationAdapter;

    private Map<PhoneNumber, OTPSystem> otpData = new HashMap<>();

    @Autowired
    public OtpVerificationService(ITwilioOtpVerificationAdapter otpVerificationAdapter) {
        this.otpVerificationAdapter = otpVerificationAdapter;
    }

    @Override
    public ResponseEntity<Object> sendOtp(PhoneNumber phoneNumber) {

        System.out.println("LOG:  OtpVerificationService sendOtp()  phone number  " + phoneNumber.getPhoneNumber());

        OTPSystem otpSystem = setOtpInfo(phoneNumber);
        otpData.put(phoneNumber, otpSystem);

        System.out.println("LOG:  OtpVerificationServiceII sendOtp()  otpDaata - hasMap values  " +
                otpData.get(phoneNumber).getOtpCode() + "\n" +
                otpData.get(phoneNumber).getPhoneNumber().getPhoneNumber() + "\n" + otpData.get(phoneNumber).getExpiryTime());

        return otpVerificationAdapter.sendOtp(phoneNumber, otpData);
    }


    @Override
    public ResponseEntity<Object> verifyOtp(OTPVerifyPayload otpVerifyPayload) {

        System.out.println("LOG:  OtpVerificationServiceI verifyOtp()  phone number " +
                otpVerifyPayload.getPhoneNumber().getPhoneNumber() + "  otpCode" + otpVerifyPayload.getOtpCode());

        OTPSystem otpSystem = otpData.get(otpVerifyPayload.getPhoneNumber());

        System.out.println("LOG:  OtpVerificationServiceI verifyOtp()  phone number " + otpSystem.getPhoneNumber()
                .getPhoneNumber() + "  otpCode" + otpSystem.getOtpCode() + "  " + " expiration time in "
                + (Long.valueOf(otpSystem.getExpiryTime()) - System.currentTimeMillis()) + " seconds");


        if (Long.valueOf(otpSystem.getExpiryTime()) > System.currentTimeMillis()) {
            //TODO: verify the time is valid

            System.out.println("Inside If stmnt - checking time");
            if (otpSystem.getOtpCode() == otpVerifyPayload.getOtpCode()) {
                System.out.println("Inside If stmnt - checking otp");
                otpData.remove(otpSystem.getPhoneNumber().getPhoneNumber());

                return new ResponseEntity<>("OTP Code has been successfully verified", HttpStatus.OK);
            } else if (otpSystem.getOtpCode() != otpVerifyPayload.getOtpCode()) {
                return new ResponseEntity<>("OTP Code is not correct", HttpStatus.BAD_GATEWAY);
            }
        }

        return new ResponseEntity<>("OTP Code has been expired", HttpStatus.BAD_REQUEST);
    }

    private OTPSystem setOtpInfo(PhoneNumber phoneNumber) {
        System.out.println("LOG:  OtpVerificationService setOtpInfo()  phone number " + phoneNumber.getPhoneNumber());

        Random random = new Random();
        int low = 1001; //TODO: make it a 6 digit number - just add 2 zeros and 2 nines
        int high = 9999;
        int otpCode = random.nextInt(high - low) + low;

  /*      Long now = System.currentTimeMillis();
        Long fiveMinutesFromNow = now + TimeUnit.MINUTES.toMillis(1);
        String expireTime = fiveMinutesFromNow.toString();
*/
        String expireTime = String.valueOf(System.currentTimeMillis() + 60000);//600000

      /*  Instant instant = Instant.now();
        Long seconds = TimeUnit.MINUTES.toSeconds( 5 );
        Instant fiveMinutesLater = instant.plusSeconds( seconds );
        String expireTime = fiveMinutesLater.toString();

       */

        OTPSystem otp = new OTPSystem(otpCode, phoneNumber, expireTime);

      /*  //get current Time
        long currentTime = System.currentTimeMillis();
    //now add half an hour, 1 800 000 milli seconds = 30 minutes
        long halfAnHourLater = currentTime + 1800000;*/


        return otp;
    }


}
