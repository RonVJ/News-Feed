package com.casestudy.newsfeed.commands;

import com.casestudy.newsfeed.controllers.FeedComponentController;
import com.casestudy.newsfeed.dtos.FeedComponentRequestDto;
import com.casestudy.newsfeed.dtos.FeedComponentResponseDto;
import com.casestudy.newsfeed.dtos.FollowRequestDto;
import com.casestudy.newsfeed.dtos.FollowResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Getter
@Setter
public class PostCommand implements Command{
    FeedComponentController feedComponentController;

    @Autowired
    public PostCommand(FeedComponentController feedComponentController) {
        this.feedComponentController = feedComponentController;
    }

    @Override
    public boolean matches(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        if(tokens.size() == 2 && tokens.get(0).equalsIgnoreCase(CommandKeywords.POST_COMMAND)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        if(!CommandRegistry.checkIfValidSession()) {
            System.out.println("Invalid Session. Please login");
            return;
        }
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        FeedComponentRequestDto request = new FeedComponentRequestDto();
        request.setUserId(CommandRegistry.currentSession.getCreatedBy().getId());
        request.setDescription(tokens.get(1));

        FeedComponentResponseDto response = feedComponentController.post(request);
    }
}
