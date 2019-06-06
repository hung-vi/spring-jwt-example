package com.vnext.security.jwtex.api.exceptions;


import com.vnext.security.jwtex.models.UserPrincipal;

public class UnknownUserPrincipalException extends RuntimeException {

    public UnknownUserPrincipalException(UserPrincipal _userPrincipal) {
        super(String.format("unknown user principal: %s", _userPrincipal.toString()));
    }

    public UnknownUserPrincipalException(String _message) {
        super(_message);
    }


}
