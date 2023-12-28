package com.example.data_api.api.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String gender;
    private String studentCardId;
    private String oneSignalId;
    private Boolean isStudent;
    private Boolean isDeleted;
}
