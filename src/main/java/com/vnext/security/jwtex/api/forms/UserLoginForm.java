package com.vnext.security.jwtex.api.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vnext.security.jwtex.utils.validation.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserLoginForm {

    @Getter
    @Setter
    @JsonProperty(required = true)
    @NotNull
    @Length(max = 128, message = "email must be less than 128 characters")
    @Email
    private String email;

    @Getter
    @Setter
    @JsonProperty(required = true)
    @NotNull
    @Length(min = 8, max = 128)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)[!-~]*$")
    private String password;
}
