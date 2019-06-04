package com.vnext.security.jwtex.models;

import lombok.Value;

import java.util.Set;

@Value
public class User {

    Long id;

    Set<String> authorities;

    String email;

    String firstName;

    String lastName;

    String password;
}
