package com.casestudy.newsfeed.dtos;

import com.casestudy.newsfeed.models.FeedComponent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CommentRequestDto extends FeedComponentRequestDto {
    private Long parentFeedComponentId;
}
