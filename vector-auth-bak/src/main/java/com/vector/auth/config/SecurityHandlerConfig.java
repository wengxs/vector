package com.vector.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vector.common.core.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

/**
 * Security认证处理器
 *
 * Created by Zorg
 * 2020/5/23 17:05
 */
//@Configuration
@Slf4j
public class SecurityHandlerConfig {

//    @Autowired
//    private ObjectMapper objectMapper;
//    @Autowired
//    private TokenService tokenService;
//    @Autowired
//    private SecurityProperties securityProperties;
//
//    /**
//     * 登录成功
//     */
//    @Bean
//    public AuthenticationSuccessHandler loginSuccessHandler() {
//        return new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                                Authentication authentication)
//                    throws IOException, ServletException {
//                log.info("登录成功");
//                log.info("onAuthenticationSuccess().authentication={}", JSON.toJSONString(authentication));
//                log.info("onAuthenticationSuccess().getPrincipal={}", JSON.toJSONString(authentication.getPrincipal()));
//                LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//                response.setContentType("application/json;charset=UTF-8");
//                response.getWriter().write(objectMapper.writeValueAsString(R.ok(tokenService.createToken(loginUser))));
//            }
//        };
//    }
//
//    /**
//     * 登录失败
//     */
//    @Bean
//    public AuthenticationFailureHandler loginFailureHandler() {
//        return new AuthenticationFailureHandler() {
//            @Override
//            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//                                                AuthenticationException exception)
//                    throws IOException, ServletException {
//                log.info("登录失败");
////                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//                response.setContentType("application/json;charset=UTF-8");
//                if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
//                    log.error("用户不存在：{}", exception.getMessage());
//                    response.getWriter().write(objectMapper.writeValueAsString(R.fail("用户名或密码有误")));
//                } else if (exception instanceof LockedException) {
//                    log.error("账号已锁定：{}", exception.getMessage());
//                    response.getWriter().write(objectMapper.writeValueAsString(R.fail("账号已锁定")));
//                } else if (exception instanceof CredentialsExpiredException) {
//                    log.error("证书过期：{}", exception.getMessage());
//                    response.getWriter().write(objectMapper.writeValueAsString(R.fail("证书过期")));
//                } else if (exception instanceof AccountExpiredException) {
//                    log.error("账号已过期：{}", exception.getMessage());
//                    response.getWriter().write(objectMapper.writeValueAsString(R.fail("账号已过期")));
//                } else if (exception instanceof DisabledException) {
//                    log.error("账号已禁用：{}", exception.getMessage());
//                    response.getWriter().write(objectMapper.writeValueAsString(R.fail("账号已禁用")));
//                } else {
//                    log.error("登录失败：{}", exception.getMessage());
//                    response.getWriter().write(objectMapper.writeValueAsString(R.fail("登录失败")));
//                }
//            }
//        };
//    }
//
//    /**
//     * 未登录
//     */
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        return new AuthenticationEntryPoint() {
//            @Override
//            public void commence(HttpServletRequest request, HttpServletResponse response,
//                                 AuthenticationException authException)
//                    throws IOException, ServletException {
//                log.info("未登录：{}", authException.getMessage());
//                response.setStatus(HttpStatus.OK.value());
//                response.setContentType("application/json;charset=UTF-8");
//                response.getWriter().write(objectMapper.writeValueAsString(
//                        R.fail(HttpStatus.UNAUTHORIZED.value(), "没有权限访问")
//                ));
//            }
//        };
//    }
//
//    /**
//     * 退出登录
//     */
//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() {
//        return new LogoutSuccessHandler() {
//            @Override
//            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication)
//                    throws IOException, ServletException {
//                String authHeader = request.getHeader(securityProperties.getToken().getHeader());
//                if (authHeader != null && authHeader.startsWith(securityProperties.getToken().getPrefix())) {
//                    String authToken = authHeader.substring(securityProperties.getToken().getPrefix().length());
//                    tokenService.deleteToken(authToken);
//                }
//                log.info("退出登录成功");
//                response.setContentType("application/json;charset=UTF-8");
//                response.getWriter().write(objectMapper.writeValueAsString(R.ok()));
//            }
//        };
//    }

}
