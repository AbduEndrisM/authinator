package com.jeba.authinator.controller;

import com.jeba.authinator.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/user/")
public class UserController {
//    private final IUserService userService;


    @PostMapping("create")
    public String saveUser(@RequestBody String phoneNumber) {

//        return userService.saveUser(phoneNumber);
        return null;
    }
}
