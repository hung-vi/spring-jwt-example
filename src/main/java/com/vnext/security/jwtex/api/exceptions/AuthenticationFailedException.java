package com.vnext.security.jwtex.api.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthenticationFailedException extends RuntimeException {

    public AuthenticationFailedException() {
        super();
    }

    public AuthenticationFailedException(String _message) {
        super(_message);
    }

    public AuthenticationFailedException(Throwable _cause) {
        super(_cause);
    }

    public AuthenticationFailedException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
