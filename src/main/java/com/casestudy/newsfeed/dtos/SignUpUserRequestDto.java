package com.casestudy.newsfeed.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpUserRequestDto {
    private String userName;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String email;
}
