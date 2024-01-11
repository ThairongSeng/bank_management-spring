package com.example.data_api.api.user;


import com.example.data_api.api.auth.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    //auth
    private String email;
    private String password;
    private Boolean isVerified;
    private String verifiedCode;


    //user has roles
    private List<Role> roles;
}
