package com.casestudy.newsfeed.commands;

import com.casestudy.newsfeed.controllers.UserController;
import com.casestudy.newsfeed.dtos.LoginUserRequestDto;
import com.casestudy.newsfeed.dtos.LoginUserResponseDto;
import com.casestudy.newsfeed.models.SessionStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Component
public class LoginCommand implements Command{
    private UserController userController;

    @Autowired
    public LoginCommand(UserController userController) {
        this.userController = userController;
    }


    @Override
    public boolean matches(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        if(tokens.size() == 3 && tokens.get(0).equalsIgnoreCase(CommandKeywords.LOGIN_COMMAND)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        LoginUserRequestDto request = new LoginUserRequestDto();
        request.setUserName(tokens.get(1));
        request.setPassword(tokens.get(2));

        LoginUserResponseDto response = userController.login(request);
        CommandRegistry.currentSession.setCreatedBy(response.getSession().getCreatedBy());
        CommandRegistry.currentSession.setSessionStatus(SessionStatus.ACTIVE);
    }
}
