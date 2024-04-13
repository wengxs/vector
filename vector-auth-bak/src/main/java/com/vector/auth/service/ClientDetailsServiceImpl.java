package com.vector.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.info("loadClientByClientId({})", clientId);
        // 后面通过feign从管理端获取，目前写死
        BaseClientDetails clientDetails = new BaseClientDetails(
                "admin-system",
                "",
                "all",
                "password,client_credentials,refresh_token,authorization_code",
                "",
                "http://www.baidu.com"

        );
        clientDetails.setClientSecret(new BCryptPasswordEncoder().encode("vector"));
        clientDetails.setAccessTokenValiditySeconds(3600);
        clientDetails.setRefreshTokenValiditySeconds(36000000);
        return clientDetails;
    }
}
