package com.vector.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetailsService {

    UserDetails retrieveUser(String keyword) throws RuntimeException;
}
