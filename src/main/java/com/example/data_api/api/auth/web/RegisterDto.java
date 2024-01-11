package com.example.data_api.api.auth.web;

import com.example.data_api.api.user.validator.email.EmailUnique;
import com.example.data_api.api.user.validator.password.Password;
import com.example.data_api.api.user.validator.password.PasswordMatch;
import com.example.data_api.api.user.validator.role.RoleIdConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;


@PasswordMatch(message = "Your password is not match!",password = "password", confirmPassword = "confirmedPassword")
public record RegisterDto(@NotBlank(message = "Email is required")
                          @EmailUnique
                          @Email
                          String email,

                          @Password
                          @NotBlank(message = "Password is required")
                          String password,

                          @Password
                          @NotBlank(message = "Confirmed password is required")
                          String confirmedPassword,

                          @NotNull(message = "Roles are required")
                          @RoleIdConstraint
                          List<Integer> roleIds) {
}
