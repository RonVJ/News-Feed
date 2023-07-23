package com.casestudy.newsfeed.dtos;

import com.casestudy.newsfeed.models.FeedComponent;
import com.casestudy.newsfeed.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class UserFeedComponentRequestDto {
    private Long userId;
    private Long feedComponentId;
}
