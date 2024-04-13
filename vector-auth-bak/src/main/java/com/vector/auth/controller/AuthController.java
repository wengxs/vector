package com.vector.auth.controller;

import com.vector.common.core.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
@Slf4j
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @PostMapping("/token")
    public R<OAuth2AccessToken> postAccessToken(Principal principal,
                                                @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {
        log.info("用户登录postAccessToken");
        String clientId = parameters.get("client_id");
        log.info("clientId={},getName={}", clientId, principal.getName());
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        return R.ok(oAuth2AccessToken);
    }

}
