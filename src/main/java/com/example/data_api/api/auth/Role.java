package com.example.data_api.api.auth;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements GrantedAuthority {
    private Integer id;
    private String name;

    @Override
    public String getAuthority() {
        return "ROLE_" + name; //ROLE_CUSTOMER
    }
}
