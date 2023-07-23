package com.casestudy.newsfeed.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedComponentRequestDto {
    private Long userId;
    private String description;
}
