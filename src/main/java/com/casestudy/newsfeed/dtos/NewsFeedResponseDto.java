package com.casestudy.newsfeed.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class NewsFeedResponseDto extends BaseResponseDto {
    private String personalizedNewsFeed;
}
