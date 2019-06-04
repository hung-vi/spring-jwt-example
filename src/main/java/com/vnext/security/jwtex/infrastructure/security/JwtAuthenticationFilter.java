package com.vnext.security.jwtex.infrastructure.security;

import com.vnext.security.jwtex.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest _request, HttpServletResponse _response, FilterChain _filterChain) throws ServletException, IOException {
        String jwt = getToken(_request);
    }


    private String getToken(HttpServletRequest _request) {
        String bearerToken = _request.getHeader("Authorization");
        if (StringUtils.containsAny(bearerToken, "bearer") &&
            StringUtils.startsWith(bearerToken, "bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
