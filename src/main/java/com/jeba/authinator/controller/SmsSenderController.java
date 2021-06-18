package com.jeba.authinator.controller;

import com.jeba.authinator.domain.Sms;
import com.jeba.authinator.service.ISmsSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/sms/")
@Slf4j
public class SmsSenderController {

    private final ISmsSenderService ISmsSender;

    @Autowired
    public SmsSenderController(ISmsSenderService ISmsSender) {
        this.ISmsSender = ISmsSender;
    }

    @PostMapping("send")
    public void sendSms(@RequestBody @Valid Sms sms){
        ISmsSender.sendSms(sms);
    }
}
