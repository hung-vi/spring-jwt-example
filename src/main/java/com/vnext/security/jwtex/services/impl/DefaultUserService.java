package com.vnext.security.jwtex.services.impl;

import com.vnext.security.jwtex.api.exceptions.AuthenticationFailedException;
import com.vnext.security.jwtex.api.exceptions.ResourceException;
import com.vnext.security.jwtex.api.exceptions.ResourceNotFoundException;
import com.vnext.security.jwtex.api.exceptions.ResourceViolationException;
import com.vnext.security.jwtex.models.Password;
import com.vnext.security.jwtex.models.User;
import com.vnext.security.jwtex.models.UserPrincipal;
import com.vnext.security.jwtex.repositories.UserRepository;
import com.vnext.security.jwtex.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService {

    final UserRepository userRepository;

    @Transactional(rollbackFor = ResourceException.class)
    @Override
    public UserDetails loadUserByUserId(@NonNull Long _id) {
        User user =
            this.userRepository.findById(_id)
                .orElseThrow(() ->
                                 new UsernameNotFoundException(String.format("user not found with email \"%s\"", _id)));
        return UserPrincipal.of(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserDetails loadUserByUsername(String _email) throws UsernameNotFoundException {
        User user =
            this.userRepository.findByEmail(_email)
                .orElseThrow(() ->
                                 new UsernameNotFoundException(String.format("user not found with email \"%s\"", _email)));
        return UserPrincipal.of(user);
    }

    @Transactional(rollbackFor = ResourceException.class)
    @Override
    public User createUser(@NonNull String _email, @NonNull String _firstName, @NonNull String _lastName, @NonNull String _password) throws ResourceViolationException {
        Set<String> authorities = new HashSet<>();
        authorities.add("USER");
        String hashedPassword = BCrypt.hashpw(_password, BCrypt.gensalt());
        User user = new User(null, authorities, _email, _firstName, _lastName, hashedPassword);
        checkDuplicateEmail(user);
        user = userRepository.insert(user);
        return user;
    }


    @Transactional(readOnly = true, rollbackFor = ResourceException.class)
    @Override
    public User getUser(@NonNull String _email) throws ResourceNotFoundException {
        Optional<User> user = this.userRepository.findByEmail(_email);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException(String.format("user \"%s\" not found", _email));
        }
        return user.get();
    }


    @Override
    public User authenticate(@NonNull String _email, @NonNull String _password) throws ResourceNotFoundException, AuthenticationFailedException {
        User user = getUser(_email);
        Password pwd = Password.ofHash(user.getPassword());
        if (pwd.matchs(_password)) {
            return user;
        }
        throw new AuthenticationFailedException("Login failed. Entered an invalid email or password");
    }


    private void checkDuplicateEmail(User _user) throws ResourceViolationException {
        Optional<User> user = this.userRepository.findByEmail(_user.getEmail());
        if (user.isPresent()) {
            throw new ResourceViolationException(String.format("user \"%s\" already exists", _user.getEmail()));
        }
    }

}
