package com.casestudy.newsfeed.controllers;

import com.casestudy.newsfeed.dtos.CommentRequestDto;
import com.casestudy.newsfeed.dtos.FeedComponentRequestDto;
import com.casestudy.newsfeed.dtos.FeedComponentResponseDto;
import com.casestudy.newsfeed.exceptions.FeedComponentDoesNotExist;
import com.casestudy.newsfeed.exceptions.UserDoesNotExistException;
import com.casestudy.newsfeed.models.Comment;
import com.casestudy.newsfeed.models.Post;
import com.casestudy.newsfeed.services.FeedComponentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@Getter
@Setter
public class FeedComponentController {
    private FeedComponentService feedComponentService;

    @Autowired
    public FeedComponentController(FeedComponentService feedComponentService) {
        this.feedComponentService = feedComponentService;
    }

    public FeedComponentResponseDto post(FeedComponentRequestDto request) {
        Long userId = request.getUserId();
        String description = request.getDescription();

        FeedComponentResponseDto response = new FeedComponentResponseDto();

        try {
            Post post = feedComponentService.post(userId, description);
            response.setId(post.getId());
            response.setStatus("SUCCESS");
        }
        catch (UserDoesNotExistException userDoesNotExistException) {
            response.setStatus("FAILURE");
            response.setMessage(userDoesNotExistException.getMessage());
        }
        return response;
    }

    public FeedComponentResponseDto comment(CommentRequestDto request) {
        Long userId = request.getUserId();
        String description = request.getDescription();
        Long parentFeedComponentId = request.getParentFeedComponentId();

        FeedComponentResponseDto response = new FeedComponentResponseDto();

        try {
            Comment comment = feedComponentService.comment(userId, description, parentFeedComponentId);
            response.setId(comment.getId());
            response.setStatus("SUCCESS");
        }
        catch (UserDoesNotExistException userDoesNotExistException) {
            response.setStatus("FAILURE");
            response.setMessage(userDoesNotExistException.getMessage());
        } catch (FeedComponentDoesNotExist feedComponentDoesNotExist) {
            response.setStatus("FAILURE");
            response.setMessage(feedComponentDoesNotExist.getMessage());
        }
        return response;
    }
}
