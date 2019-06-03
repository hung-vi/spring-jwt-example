package com.vnext.security.jwtex.repositories;

import com.vnext.security.jwtex.api.exceptions.ResourceNotFoundException;
import com.vnext.security.jwtex.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository {

    User insert(User _user);

    Optional<User> findByEmail(String _email);

}
