package com.vnext.security.jwtex.repositories;

import com.vnext.security.jwtex.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository {

    Optional<User> findById(Long _id);

    Optional<User> findByEmail(String _email);

    User insert(User _user);

}
