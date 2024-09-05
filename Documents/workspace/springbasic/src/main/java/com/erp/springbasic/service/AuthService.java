package com.erp.springbasic.service;

import com.erp.springbasic.dto.PostUserRequestDto;
import com.erp.springbasic.dto.SignInRequestDto;

public interface AuthService {
    String signUp(PostUserRequestDto dto);
    String signIn(SignInRequestDto dto);
}
