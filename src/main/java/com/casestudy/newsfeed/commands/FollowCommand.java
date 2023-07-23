package com.casestudy.newsfeed.commands;

import com.casestudy.newsfeed.controllers.FollowController;
import com.casestudy.newsfeed.dtos.FollowRequestDto;
import com.casestudy.newsfeed.dtos.FollowResponseDto;
import com.casestudy.newsfeed.models.Follow;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Getter
@Setter
public class FollowCommand implements Command{
    private FollowController followController;

    @Autowired
    public FollowCommand(FollowController followController) {
        this.followController = followController;
    }

    @Override
    public boolean matches(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();

        if(tokens.size() == 2 && tokens.get(0).equalsIgnoreCase(CommandKeywords.FOLLOW_COMMAND)) {
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
        FollowRequestDto request = new FollowRequestDto();
        request.setFollowedBy(CommandRegistry.currentSession.getCreatedBy().getUserName());
        request.setFollowedTo(tokens.get(1));

        FollowResponseDto response = followController.follow(request);
    }
}
