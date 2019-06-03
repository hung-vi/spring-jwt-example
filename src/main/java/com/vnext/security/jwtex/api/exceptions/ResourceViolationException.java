package com.vnext.security.jwtex.api.exceptions;


public class ResourceViolationException extends ResourceException {

    public ResourceViolationException() {
        super();
    }

    public ResourceViolationException(String _message) {
        super(_message);
    }

    public ResourceViolationException(Throwable _cause) {
        super(_cause);
    }

    public ResourceViolationException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
