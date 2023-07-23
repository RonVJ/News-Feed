package com.casestudy.newsfeed.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseDto {
    private Long Id;
    private String status;
    private String message;
}
