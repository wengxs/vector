package com.vector.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wengxs
 */
@SpringBootApplication
public class AuthApplication2 {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication2.class, "--server.port=10001");
    }
}
