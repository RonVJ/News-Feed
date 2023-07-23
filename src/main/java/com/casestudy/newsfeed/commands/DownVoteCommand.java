package com.casestudy.newsfeed.commands;

import com.casestudy.newsfeed.controllers.UserFeedComponentController;
import com.casestudy.newsfeed.dtos.UserFeedComponentRequestDto;
import com.casestudy.newsfeed.dtos.UserFeedComponentResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Component
public class DownVoteCommand implements Command{
    UserFeedComponentController userFeedComponentController;

    @Autowired
    public DownVoteCommand(UserFeedComponentController userFeedComponentController) {
        this.userFeedComponentController = userFeedComponentController;
    }

    @Override
    public boolean matches(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        if(tokens.size() == 2 && tokens.get(0).equalsIgnoreCase(CommandKeywords.DOWNVOTE_COMMAND)) {
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
        UserFeedComponentRequestDto request = new UserFeedComponentRequestDto();
        request.setUserId(CommandRegistry.currentSession.getCreatedBy().getId());
        request.setFeedComponentId(Long.parseLong(tokens.get(1)));

        UserFeedComponentResponseDto response = userFeedComponentController.downVote(request);
    }
}
