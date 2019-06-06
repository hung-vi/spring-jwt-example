package com.vnext.security.jwtex.api.controllers;

import com.vnext.security.jwtex.api.exceptions.ResourceViolationException;
import com.vnext.security.jwtex.api.forms.UserCreateForm;
import com.vnext.security.jwtex.api.forms.UserLoginForm;
import com.vnext.security.jwtex.api.responses.CreatedResponse;
import com.vnext.security.jwtex.api.responses.JwtAuthenticationResponse;
import com.vnext.security.jwtex.api.views.UserView;
import com.vnext.security.jwtex.infrastructure.security.JwtTokenProvider;
import com.vnext.security.jwtex.models.User;
import com.vnext.security.jwtex.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    final UserService userService;

    final AuthenticationManager authenticationManager;

    final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/signup")
    public CreatedResponse<UserView> create(@RequestBody @Valid UserCreateForm _form) throws ResourceViolationException {
        User createdUser = userService.createUser(_form.getEmail(),
                                    _form.getFirstName(),
                                    _form.getLastName(),
                                    _form.getPassword());
        UserView view = new UserView(createdUser);
        return new CreatedResponse<>(view);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginForm _form) {
        Authentication authentication = authenticationManager.authenticate (
            new UsernamePasswordAuthenticationToken(_form.getEmail(), _form.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


}
