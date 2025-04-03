package com.vector.auth.controller;

import com.vector.auth.service.AuthService;
import com.vector.common.core.result.R;
import com.vector.common.web.annotation.PreventRepeatSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/smsCode")
    @PreventRepeatSubmit
    public R<?> smsCode(@RequestParam String mobile) {
        authService.sendLoginSmsCode(mobile);
        return R.ok();
    }
}
