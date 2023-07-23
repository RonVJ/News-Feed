package com.casestudy.newsfeed.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class LoginUserRequestDto {
    private String userName;
    private String password;
}
