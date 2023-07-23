package com.casestudy.newsfeed.services;

import com.casestudy.newsfeed.exceptions.PasswordDoesNotMatchException;
import com.casestudy.newsfeed.exceptions.UserAlreadyExistException;
import com.casestudy.newsfeed.exceptions.UserDoesNotExistException;
import com.casestudy.newsfeed.models.Session;
import com.casestudy.newsfeed.models.User;
import com.casestudy.newsfeed.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class UserService {
    UserRepository userRepository;
    SessionService sessionService;
    FollowService followService;

    @Autowired
    public UserService(UserRepository userRepository
            , SessionService sessionService
            , FollowService followService) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
        this.followService = followService;
    }

    public User signUp(String userName
            , String password
            , String fullName
            , String phoneNumber
            , String email) throws UserAlreadyExistException {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if(!userOptional.isEmpty()) {
            throw new UserAlreadyExistException();
        }

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);

        return userRepository.save(user);
    }

    public Session login(String userName
            , String password) throws UserDoesNotExistException, PasswordDoesNotMatchException {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if(userOptional.isEmpty()) {
            throw new UserDoesNotExistException();
        }

        User user = userOptional.get();
        if(!user.getPassword().equals(password)) {
            throw new PasswordDoesNotMatchException();
        }
        return sessionService.createSession(user.getId());
    }

    public String showNewsFeed(Long userId) throws UserDoesNotExistException{
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new UserDoesNotExistException();
        }
        User user = userOptional.get();
        List<User> followingList = followService.getFollowRepository().findAllByFollowedBy(user);

        return "";
    }
}
