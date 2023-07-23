package com.casestudy.newsfeed.commands;

import com.casestudy.newsfeed.controllers.UserController;
import com.casestudy.newsfeed.dtos.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Component
public class ShowNewsFeedCommand implements Command{
    private UserController userController;

    public ShowNewsFeedCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean matches(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        if(tokens.size() == 1 && tokens.get(0).equalsIgnoreCase(CommandKeywords.SHOWNEWSFEED_COMMAND)) {
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
        NewsFeedRequestDto request = new NewsFeedRequestDto();
        request.setUserId(CommandRegistry.currentSession.getCreatedBy().getId());

        NewsFeedResponseDto response = userController.showNewsFeed(request);
        System.out.println(response.getPersonalizedNewsFeed());
    }
}
