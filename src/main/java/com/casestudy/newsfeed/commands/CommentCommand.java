package com.casestudy.newsfeed.commands;

import com.casestudy.newsfeed.controllers.FeedComponentController;
import com.casestudy.newsfeed.dtos.CommentRequestDto;
import com.casestudy.newsfeed.dtos.FeedComponentRequestDto;
import com.casestudy.newsfeed.dtos.FeedComponentResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Component
public class CommentCommand implements Command{
    FeedComponentController feedComponentController;

    @Autowired
    public CommentCommand(FeedComponentController feedComponentController) {
        this.feedComponentController = feedComponentController;
    }

    @Override
    public boolean matches(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        if(tokens.size() == 3 && tokens.get(0).equalsIgnoreCase(CommandKeywords.COMMENT_COMMAND)) {
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
        CommentRequestDto request = new CommentRequestDto();
        request.setUserId(CommandRegistry.currentSession.getCreatedBy().getId());
        request.setDescription(tokens.get(1));
        request.setParentFeedComponentId(Long.parseLong(tokens.get(2)));

        FeedComponentResponseDto response = feedComponentController.comment(request);
    }
}
