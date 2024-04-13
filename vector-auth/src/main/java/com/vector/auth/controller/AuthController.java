package com.vector.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vector.auth.domain.LoginForm;
import com.vector.common.core.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
//@RequestMapping("/oauth")
@Slf4j
public class AuthController {

//    @Autowired
//    private TokenEndpoint tokenEndpoint;
//
//    @PostMapping("/login")
//    public R<OAuth2AccessToken> login(LoginRequest Principal principal,
//                                                     @RequestParam Map<String, String> parameters)
//            throws HttpRequestMethodNotSupportedException {
//        log.info("用户登录postAccessToken");
//        String clientId = parameters.get("client_id");
//        log.info("clientId={},getName={}", clientId, principal.getName());
//        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
//        return R.ok(oAuth2AccessToken);
//    }

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AuthenticationManager authenticationManager;



//    @PostMapping("/login")
//    public R<String> login(@RequestBody LoginForm loginForm) {
//        String username = loginForm.getUsername() != null ? loginForm.getUsername().trim() : "";
//        String password = loginForm.getPassword() != null ? loginForm.getPassword() : "";
//        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        try {
//            return R.ok(objectMapper.writeValueAsString(authentication));
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
