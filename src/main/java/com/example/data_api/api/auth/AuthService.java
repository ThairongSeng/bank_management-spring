package com.example.data_api.api.auth;

import com.example.data_api.api.auth.web.AuthDto;
import com.example.data_api.api.auth.web.LoginDto;
import com.example.data_api.api.auth.web.RegisterDto;

public interface AuthService {


    AuthDto login(LoginDto loginDto);

    void register(RegisterDto registerDto);


    void verify(String email);


    void checkVerify(String email, String verifiedCode);
}
