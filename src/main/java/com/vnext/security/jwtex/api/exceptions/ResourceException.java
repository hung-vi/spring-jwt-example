package com.vnext.security.jwtex.api.exceptions;

public class ResourceException extends Exception {

    ResourceException() {
        super();
    }

    ResourceException(String _message) {
        super(_message);
    }

    ResourceException(Throwable _cause) {
        super(_cause);
    }

    ResourceException(String _message, Throwable _cause) {
        super(_message, _cause);
    }
}
