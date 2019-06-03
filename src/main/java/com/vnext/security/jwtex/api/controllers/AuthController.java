package com.vnext.security.jwtex.api.controllers;

import com.vnext.security.jwtex.api.exceptions.ResourceViolationException;
import com.vnext.security.jwtex.api.forms.UserCreateForm;
import com.vnext.security.jwtex.api.forms.UserLoginForm;
import com.vnext.security.jwtex.api.responses.CreatedResponse;
import com.vnext.security.jwtex.api.responses.ResourceResponse;
import com.vnext.security.jwtex.api.views.UserView;
import com.vnext.security.jwtex.models.User;
import com.vnext.security.jwtex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public CreatedResponse<UserView> create(@RequestBody @Valid UserCreateForm _form) throws ResourceViolationException {
        User createdUser = userService.createUser(_form.getEmail(),
                                    _form.getFirstName(),
                                    _form.getLastName(),
                                    _form.getPassword());
        UserView view = new UserView(createdUser);
        return new CreatedResponse<>(view);
    }


}
