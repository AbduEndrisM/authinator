//package com.jeba.authinator.service;
//
//import com.jeba.authinator.adapter.ITwilioOtpVerificationAdapter;
//import com.jeba.authinator.domain.entity.OtpEntity;
//import com.jeba.authinator.domain.payload.OTPVerifyPayload;
//import com.jeba.authinator.domain.PhoneNumber;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//@Service
//@RequiredArgsConstructor
//public class OtpVerificationService implements IOtpVerificationService {
//
//    private final ITwilioOtpVerificationAdapter otpVerificationAdapter;
//
//    //    private Set<Map<PhoneNumber, OTPSystem>> otpDataList = new HashSet<>();
//    private Map<String, OtpEntity> otpData = new HashMap<>();
//
//    @Override
//    public ResponseEntity<Object> sendOtp(PhoneNumber phoneNumber) {/*
//String to = phoneNumber.getPhoneNumber();
//
////        Boolean numberOfRequests =  verifyNumberOfRequests(phoneNumber);
//
//        System.out.println("LOG:  OtpVerificationService sendOtp()  phone number  " + to);
//
////        Map<PhoneNumber, OTPSystem>otpData = new HashMap<>();
//        OtpEntity otpEntity = setOtpInfo(to);
//        System.out.println("============================");
//        System.out.println(otpEntity);
//        System.out.println("============================");
//
//
//        otpData.put(phoneNumber, otpEntity);
//
//        System.out.println(otpData);
//        System.out.println("============================ " + otpData.get(phoneNumber).getPhoneNumber());
//
//
////        System.out.println("OOOOOOOOOOOO  "+ otpDataList.iterator().next().get(phoneNumber));
//        System.out.println("LOG:  OtpVerificationServiceII sendOtp()  otp Data - hasMap values  " +
//                otpData.get(phoneNumber).getOtpCode() + "\n" +
//                otpData.get(phoneNumber).getPhoneNumber() + "\n" + otpData.get(phoneNumber).getExpiryTime());
//
//        System.out.println("+++++++++++++++++++++++++");
//        System.out.println(otpData.get(phoneNumber));
//        System.out.println();
////        pNumber = phoneNumber;
//        return otpVerificationAdapter.sendOtp(phoneNumber, otpEntity);*/
//    }
///*
//    private Boolean verifyNumberOfRequests(String phoneNumber) {
//
//OTPSystem otpSystem  = otpData.get(phoneNumber);
//        if(System.currentTimeMillis()<(Long.valueOf(otpSystem.getExpiryTime())+30000)){
////        if (Long.valueOf(otpSystem.getExpiryTime()) > System.currentTimeMillis()) {
//            System.out.println(System.currentTimeMillis());
//            System.out.println(otpSystem.getExpiryTime());
//            System.out.println(Long.valueOf(otpSystem.getExpiryTime()));
//            System.out.println("Please wait for half time!");
//            return new ResponseEntity<>("Wait for a moment. The otp may arrive", HttpStatus.BAD_REQUEST);
//        }
//
//        return false;
//    }*/
//
//
//    @Override
//    public ResponseEntity<Object> verifyOtp(OTPVerifyPayload otpVerifyPayload) {
//
//
//        String phoneNumber = otpVerifyPayload.getPhoneNumber();
//
//
//        System.out.println(otpVerifyPayload.getPhoneNumber());
//        System.out.println("+++++++++++++++");
//        System.out.println(otpData.get(phoneNumber).getOtpCode());
//
//
//
//
////        OTPSystem otpSystem = otpData.get(otpVerifyPayload.getPhoneNumber());
//        OtpEntity otpEntity = otpData.get(phoneNumber);
//
//        System.out.println(otpEntity);
////        System.out.println(otpData.get(otpVerifyPayload.getPhoneNumber()).getOtpCode());
//
//        System.out.println("LOG:  OtpVerificationServiceI verifyOtp()  phone number " +
//                otpEntity.getPhoneNumber() + "  otpCode " + otpEntity.getOtpCode());
//
//        System.out.println("---------------------");
//
//
////        System.out.println("++++++++++++++++ ");
////        otpDataList.stream()
////                .forEach(x-> System.out.println(x));
////        System.out.println("++++++++++++++++ ");
//
//
//
//
////        System.out.println("LOG:  OtpVerificationServiceI verifyOtp()  phone number " + otpSystem.getPhoneNumber()
////                .getPhoneNumber() + "  otpCode" + otpSystem.getOtpCode() + "  " + " expiration time in "
////                + (Long.valueOf(otpSystem.getExpiryTime()) - System.currentTimeMillis()) + " seconds");
//
//
//        if (false/*Long.valueOf(otpSystem.getExpiryTime()) > System.currentTimeMillis()*/) {
//            //TODO: verify the time is valid
//
//            System.out.println("Inside If stmnt - checking time");
//            if (otpEntity.getOtpCode() == otpVerifyPayload.getOtpCode()) {
//                System.out.println("Inside If stmnt - checking otp");
////                otpData.remove(otpSystem.getPhoneNumber());
//// send user info to Profile api
//                return new ResponseEntity<>("OTP Code has been successfully verified", HttpStatus.OK);
//            } else if (otpEntity.getOtpCode() != otpVerifyPayload.getOtpCode()) {
//                return new ResponseEntity<>("OTP Code is not correct", HttpStatus.BAD_GATEWAY);
//            }
//        }
//
//        return new ResponseEntity<>("OTP Code has been expired", HttpStatus.BAD_REQUEST);
//    }
//
//
//    @Override
//    public ResponseEntity<Object> reSendOtp(PhoneNumber phoneNum) {
//        String phoneNumber = phoneNum.getPhoneNumber();
//        System.out.println("LOG:  OtpVerificationService reSendOtp()  phone number  " + phoneNumber);
//
//    OtpEntity otpEntity = otpData.get(phoneNumber);
//
//    if (otpEntity ==null){
//       return sendOtp(phoneNum);
//    }
//        System.out.println("exp time " + otpEntity.getExpiryTime());
//        System.out.println("current  " + System.currentTimeMillis());
////        System.out.println(System.currentTimeMillis() - (Long.valueOf(otpSystem.getExpiryTime())));
//
//
//
//        System.out.println("LOG:  OtpVerificationServiceII reSendOtp()  otp Data - hasMap values  " +
//                otpData.get(phoneNumber).getOtpCode() + "\n" +
//                otpData.get(phoneNumber).getPhoneNumber() + "\n" + otpData.get(phoneNumber).getExpiryTime());
//
//        return otpVerificationAdapter.sendOtp(phoneNumber, otpEntity/*otpData*/);
//
//    }
//
//
//    private OtpEntity setOtpInfo(String phoneNumber) {
//        System.out.println("LOG:  OtpVerificationService setOtpInfo()  phone number " + phoneNumber);
//
//        Random random = new Random();
//        int low = 1001; //TODO: make it a 6 digit number - just add 2 zeros and 2 nines
//        int high = 9999;
//        int otpCode = random.nextInt(high - low) + low;
//
//  /*      Long now = System.currentTimeMillis();
//        Long fiveMinutesFromNow = now + TimeUnit.MINUTES.toMillis(1);
//        String expireTime = fiveMinutesFromNow.toString();
//*/
//        String expireTime = String.valueOf(System.currentTimeMillis() + 60000); //is 60 secs //600000
//
//      /*  Instant instant = Instant.now();
//        Long seconds = TimeUnit.MINUTES.toSeconds( 5 );
//        Instant fiveMinutesLater = instant.plusSeconds( seconds );
//        String expireTime = fiveMinutesLater.toString();
//
//       */
//        OtpEntity otpEntity = new OtpEntity();
////        otpSystem.setExpiryTime(expireTime);
//        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(2));
//        otpEntity.setOtpCode(otpCode);
//        otpEntity.setPhoneNumber(phoneNumber);
//      /*  //get current Time
//        long currentTime = System.currentTimeMillis();
//    //now add half an hour, 1 800 000 milli seconds = 30 minutes
//        long halfAnHourLater = currentTime + 1800000;*/
//
//        System.out.println("LOG:  OtpVerificationService OTP System - Info " + otpEntity);
//
//        return otpEntity;
//    }
//
//
//}
