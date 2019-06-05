package com.vnext.security.jwtex.services;

import com.vnext.security.jwtex.api.exceptions.AuthenticationFailedException;
import com.vnext.security.jwtex.api.exceptions.ResourceNotFoundException;
import com.vnext.security.jwtex.api.exceptions.ResourceViolationException;
import com.vnext.security.jwtex.models.User;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService {

    UserDetails loadUserByUserId(@NonNull Long _id);

    User createUser(@NonNull String _email, @NonNull String _firstName, @NonNull String _lastName, @NonNull String _password) throws ResourceViolationException;

    User getUser(@NonNull String _email) throws ResourceNotFoundException;

    User authenticate(@NonNull String _email, @NonNull String _password) throws ResourceNotFoundException, AuthenticationFailedException;


}
