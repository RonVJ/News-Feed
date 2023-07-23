package com.casestudy.newsfeed.controllers;

import com.casestudy.newsfeed.commands.FollowCommand;
import com.casestudy.newsfeed.dtos.FollowRequestDto;
import com.casestudy.newsfeed.dtos.FollowResponseDto;
import com.casestudy.newsfeed.exceptions.UserAlreadyFollowsException;
import com.casestudy.newsfeed.exceptions.UserDoesNotExistException;
import com.casestudy.newsfeed.services.FollowService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Getter
@Setter
public class FollowController {
    private FollowService followService;

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    public FollowResponseDto follow(FollowRequestDto request) {
        String followedBy = request.getFollowedBy();
        String followedTo = request.getFollowedTo();
        FollowResponseDto response = new FollowResponseDto();

        try {
            followService.follow(followedBy, followedTo);
            response.setStatus("SUCCESS");
        } catch (UserDoesNotExistException userDoesNotExistException) {
            response.setStatus("FAILURE");
            response.setMessage(userDoesNotExistException.getMessage());
        } catch (UserAlreadyFollowsException userAlreadyFollowsException) {
            response.setStatus("FAILURE");
            response.setMessage(userAlreadyFollowsException.getMessage());
        }

        return response;

    }
}
