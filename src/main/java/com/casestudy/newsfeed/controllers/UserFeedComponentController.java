package com.casestudy.newsfeed.controllers;

import com.casestudy.newsfeed.dtos.UserFeedComponentRequestDto;
import com.casestudy.newsfeed.dtos.UserFeedComponentResponseDto;
import com.casestudy.newsfeed.exceptions.FeedComponentAlreadyDownVoted;
import com.casestudy.newsfeed.exceptions.FeedComponentAlreadyUpVoted;
import com.casestudy.newsfeed.exceptions.FeedComponentDoesNotExist;
import com.casestudy.newsfeed.exceptions.UserDoesNotExistException;
import com.casestudy.newsfeed.models.UserFeedComponent;
import com.casestudy.newsfeed.services.UserFeedComponentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@Controller
public class UserFeedComponentController {
    UserFeedComponentService userFeedComponentService;

    @Autowired
    public UserFeedComponentController(UserFeedComponentService userFeedComponentService) {
        this.userFeedComponentService = userFeedComponentService;
    }


    public UserFeedComponentResponseDto upVote(UserFeedComponentRequestDto request) {
        Long userId = request.getUserId();
        Long feedComponentId = request.getFeedComponentId();
        UserFeedComponentResponseDto response = new UserFeedComponentResponseDto();

        try {
            UserFeedComponent userFeedComponent = userFeedComponentService.upVote(userId, feedComponentId);
            response.setId(userFeedComponent.getId());
            response.setStatus("SUCCESS");
        }
        catch (UserDoesNotExistException userDoesNotExistException) {
            response.setStatus("FAILURE");
            response.setMessage(userDoesNotExistException.getMessage());
        }
        catch (FeedComponentDoesNotExist feedComponentDoesNotExist) {
            response.setStatus("FAILURE");
            response.setMessage(feedComponentDoesNotExist.getMessage());
        }
        catch (FeedComponentAlreadyUpVoted feedComponentAlreadyUpVoted) {
            response.setStatus("FAILURE");
            response.setMessage(feedComponentAlreadyUpVoted.getMessage());
        }

        return response;
    }

    public UserFeedComponentResponseDto downVote(UserFeedComponentRequestDto request) {
        Long userId = request.getUserId();
        Long feedComponentId = request.getFeedComponentId();
        UserFeedComponentResponseDto response = new UserFeedComponentResponseDto();

        try {
            UserFeedComponent userFeedComponent = userFeedComponentService.downVote(userId, feedComponentId);
            response.setId(userFeedComponent.getId());
            response.setStatus("SUCCESS");
        }
        catch (UserDoesNotExistException userDoesNotExistException) {
            response.setStatus("FAILURE");
            response.setMessage(userDoesNotExistException.getMessage());
        }
        catch (FeedComponentDoesNotExist feedComponentDoesNotExist) {
            response.setStatus("FAILURE");
            response.setMessage(feedComponentDoesNotExist.getMessage());
        }
        catch (FeedComponentAlreadyDownVoted feedComponentAlreadyDownVoted) {
            response.setStatus("FAILURE");
            response.setMessage(feedComponentAlreadyDownVoted.getMessage());
        }

        return response;
    }
}
