package com.vnext.security.jwtex.api.exceptions;


public class ResourceNotFoundException extends ResourceException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String _message) {
        super(_message);
    }

    public ResourceNotFoundException(Throwable _cause) {
        super(_cause);
    }

    public ResourceNotFoundException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
