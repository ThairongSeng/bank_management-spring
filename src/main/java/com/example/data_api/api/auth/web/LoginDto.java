package com.example.data_api.api.auth.web;

import com.example.data_api.api.user.validator.password.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
                    @Email
                    @NotBlank
                    String email,

                    @NotBlank
                    @Password
                    String password) {
}
