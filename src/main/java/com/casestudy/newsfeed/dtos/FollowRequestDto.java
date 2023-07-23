package com.casestudy.newsfeed.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowRequestDto {
    private String followedTo;
    private String followedBy;
}
