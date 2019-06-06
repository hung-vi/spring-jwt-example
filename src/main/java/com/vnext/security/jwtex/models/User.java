package com.vnext.security.jwtex.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    @Getter
    Long id;

    @Getter
    @Setter
    Set<String> authorities;

    @Getter
    String email;

    @Getter
    String firstName;

    @Getter
    String lastName;

    @Getter
    String password;
}
