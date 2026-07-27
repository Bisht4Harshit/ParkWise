package com.implementation.ParkWise.service;

import com.implementation.ParkWise.dto.request.LoginRequest;
import com.implementation.ParkWise.dto.request.RegisterRequest;
import com.implementation.ParkWise.dto.response.LoginResponse;

public interface AuthService {
    public String register(RegisterRequest request);

    public LoginResponse login(LoginRequest request);
}
