//package com.vector.auth.handler;
//
//import com.vector.common.core.result.R;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//@Slf4j
//public class AuthenticationExceptionHandler {
//
////    @ExceptionHandler(InvalidGrantException.class)
////    public R<?> invalidGrantException(InvalidGrantException exception) {
////        log.error("用户名或密码错误：{}", exception.getMessage(), exception);
////        return R.fail("用户名或密码错误");
////    }
//
//    @ExceptionHandler(AuthenticationException.class)
//    public R<?> authenticationExceptionHandler(AuthenticationException exception) {
//        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
//            log.error("用户不存在：{}", exception.getMessage());
//            return R.fail("用户名或密码有误");
//        } else if (exception instanceof LockedException) {
//            log.error("账号已锁定：{}", exception.getMessage());
//            return R.fail("账号已锁定");
//        } else if (exception instanceof CredentialsExpiredException) {
//            log.error("证书过期：{}", exception.getMessage());
//            return R.fail("证书过期");
//        } else if (exception instanceof AccountExpiredException) {
//            log.error("账号已过期：{}", exception.getMessage());
//            return R.fail("账号已过期");
//        } else if (exception instanceof DisabledException) {
//            log.error("账号已禁用：{}", exception.getMessage());
//            return R.fail("账号已禁用");
//        } else {
//            log.error("登录失败：{}", exception.getMessage(), exception);
//            return R.fail("登录失败");
//        }
//    }
//
//}
