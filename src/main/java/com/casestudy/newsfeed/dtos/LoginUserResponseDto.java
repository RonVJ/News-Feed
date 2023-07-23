package com.casestudy.newsfeed.dtos;

import com.casestudy.newsfeed.models.Session;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserResponseDto extends BaseResponseDto{
    private Session session;
}
