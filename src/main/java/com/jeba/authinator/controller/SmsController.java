package com.jeba.authinator.controller;

import com.jeba.authinator.domain.entity.SmsEntity;
import com.jeba.authinator.domain.payload.SmsPayload;
import com.jeba.authinator.service.ISmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/sms/")
@RequiredArgsConstructor
public class SmsController {

    private final ISmsService smsSenderService;


    @PostMapping("send")
    public SmsEntity sendSms(@RequestBody @Valid SmsPayload smsPayload) throws Throwable {

        return smsSenderService.sendSms(smsPayload);
    }
}
