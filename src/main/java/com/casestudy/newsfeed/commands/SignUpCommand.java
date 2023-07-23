package com.casestudy.newsfeed.commands;

import com.casestudy.newsfeed.controllers.UserController;
import com.casestudy.newsfeed.dtos.FeedComponentRequestDto;
import com.casestudy.newsfeed.dtos.FeedComponentResponseDto;
import com.casestudy.newsfeed.dtos.SignUpUserRequestDto;
import com.casestudy.newsfeed.dtos.SignUpUserResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Component
public class SignUpCommand implements Command {
    private UserController userController;

    @Autowired
    public SignUpCommand(UserController userController) {
        this.userController = userController;
    }


    @Override
    public boolean matches(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        if(tokens.size() == 6 && tokens.get(0).equalsIgnoreCase(CommandKeywords.SIGNUP_COMMAND)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        SignUpUserRequestDto request = new SignUpUserRequestDto();
        request.setUserName(tokens.get(1));
        request.setPassword(tokens.get(2));
        request.setFullName(tokens.get(3));
        request.setPhoneNumber(tokens.get(4));
        request.setEmail(tokens.get(5));

        SignUpUserResponseDto response = userController.signUp(request);
    }
}
