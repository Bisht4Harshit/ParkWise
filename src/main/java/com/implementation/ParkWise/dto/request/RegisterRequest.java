package com.implementation.ParkWise.dto.request;

import com.implementation.ParkWise.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String password;

    private String houseNo;

    private String street;

    private String city;

    private String state;

    private String pinCode;

    private Role role;
}
