package com.vnext.security.jwtex;

import com.vnext.security.jwtex.infrastructure.configuration.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SecurityConfig.class)
public class JwtexApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtexApplication.class, args);
    }

}
