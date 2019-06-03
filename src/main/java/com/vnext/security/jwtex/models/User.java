package com.vnext.security.jwtex.models;

import lombok.Value;

@Value
public class User {

    String id;

    String email;

    String firstName;

    String lastName;

    String password;
}
