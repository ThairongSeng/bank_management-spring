package com.example.data_api.api.user.web;

public record UserDto(String name,
                      String gender,
                      String studentCardId,
                      Boolean isStudent
                    ) {
}
