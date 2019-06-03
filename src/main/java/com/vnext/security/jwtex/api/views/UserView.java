package com.vnext.security.jwtex.api.views;

import com.vnext.security.jwtex.models.User;
import lombok.NonNull;
import lombok.Value;

@Value
public class UserView {

    String email;

    String firstName;

    String lastName;

    public UserView(@NonNull User _user) {
        this.email = _user.getEmail();
        this.firstName = _user.getFirstName();
        this.lastName = _user.getLastName();
    }
}
