package com.example.data_api.api.accounttype.web;

import jakarta.validation.constraints.NotBlank;

public record AccountTypeDto(@NotBlank(message = "Fill in the name...") String name) {
}
