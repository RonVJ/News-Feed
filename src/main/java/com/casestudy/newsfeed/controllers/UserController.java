package com.casestudy.newsfeed.controllers;

import com.casestudy.newsfeed.dtos.*;
import com.casestudy.newsfeed.exceptions.PasswordDoesNotMatchException;
import com.casestudy.newsfeed.exceptions.UserAlreadyExistException;
import com.casestudy.newsfeed.exceptions.UserDoesNotExistException;
import com.casestudy.newsfeed.models.Session;
import com.casestudy.newsfeed.models.SessionStatus;
import com.casestudy.newsfeed.models.User;
import com.casestudy.newsfeed.services.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@Controller
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpUserResponseDto signUp(SignUpUserRequestDto request) {
        SignUpUserResponseDto response = new SignUpUserResponseDto();
        String userName = request.getUserName();
        String password = request.getPassword();
        String fullName = request.getFullName();
        String phoneNumber = request.getPhoneNumber();
        String email = request.getEmail();

        try {
            User user = userService.signUp(userName, password, fullName, phoneNumber, email);
            response.setId(user.getId());
            response.setStatus("SUCCESS");
        } catch (UserAlreadyExistException userAlreadyExistException) {
            response.setStatus("FAILURE");
            response.setMessage(userAlreadyExistException.getMessage());
        }
        return response;
    }

    public LoginUserResponseDto login(LoginUserRequestDto request) {
        LoginUserResponseDto response = new LoginUserResponseDto();
        String userName = request.getUserName();
        String password = request.getPassword();
        Session session = new Session();

        try {
            session = userService.login(userName, password);
            response.setId(session.getId());
            response.setSession(session);
            response.setStatus("SUCCESS");
        } catch (UserDoesNotExistException userDoesNotExistException) {
            response.setStatus("FAILURE");
            response.setMessage(userDoesNotExistException.getMessage());
        } catch (PasswordDoesNotMatchException passwordDoesNotMatchException) {
            response.setStatus("FAILURE");
            response.setMessage(passwordDoesNotMatchException.getMessage());
        }
        return response;
    }

    public NewsFeedResponseDto showNewsFeed(NewsFeedRequestDto request) {
        Long userId = request.getUserId();
        NewsFeedResponseDto response = new NewsFeedResponseDto();
        try {
            String personalizedNewsFeed = userService.showNewsFeed(userId);
            response.setPersonalizedNewsFeed(personalizedNewsFeed);
            response.setStatus("SUCCESS");
        }
        catch (UserDoesNotExistException userDoesNotExistException) {
            response.setStatus("FAILURE");
            response.setMessage(userDoesNotExistException.getMessage());
        }
        return response;
    }
}
