package com.vnext.security.jwtex.api.controllers;

import com.vnext.security.jwtex.api.responses.ResourceResponse;
import com.vnext.security.jwtex.api.views.UserView;
import com.vnext.security.jwtex.models.User;
import com.vnext.security.jwtex.models.UserPrincipal;
import com.vnext.security.jwtex.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {


    final UserService userService;

    @GetMapping("/profile")
    public ResourceResponse<UserView> show(@AuthenticationPrincipal UserPrincipal _userprincipal) {
        User user = userService.getSelf(_userprincipal);
        UserView view = new UserView(user);
        return new ResourceResponse<>(view);
    }

}
