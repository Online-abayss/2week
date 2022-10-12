package com.sparta.article.controller;

import com.sparta.article.dto.LoginRequestDto;
import com.sparta.article.dto.SignupRequestDto;
import com.sparta.article.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService memberService;

    @PostMapping("api/users/signup")
    public String signup(@RequestBody @Valid SignupRequestDto signupRequestDto) throws IllegalAccessException {
        return memberService.registerUser(signupRequestDto);
    }

    @PostMapping("api/users/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return memberService.login(loginRequestDto, response);
    }

}
