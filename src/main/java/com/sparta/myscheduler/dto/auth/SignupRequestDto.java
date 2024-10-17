package com.sparta.myscheduler.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank(message = "username cannot be blank")
    @NotNull()
    private String username;

    @NotBlank(message = "password cannot be blank")
    @NotNull()
    private String password;

    @NotBlank(message = "passwordConfirm cannot be blank")
    @NotNull()
    private String passwordConfirm;

    @Email(message = "Invalid email format")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Invalid email format based on custom regex"
    )
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
