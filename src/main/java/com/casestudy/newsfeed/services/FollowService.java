package com.casestudy.newsfeed.services;

import com.casestudy.newsfeed.exceptions.UserAlreadyFollowsException;
import com.casestudy.newsfeed.exceptions.UserDoesNotExistException;
import com.casestudy.newsfeed.models.Follow;
import com.casestudy.newsfeed.models.User;
import com.casestudy.newsfeed.repositories.FollowRepository;
import com.casestudy.newsfeed.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@Setter
public class FollowService {
    UserRepository userRepository;
    FollowRepository followRepository;

    @Autowired
    public FollowService(UserRepository userRepository
    , FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    public Follow follow(String followedBy, String followedTo) throws UserDoesNotExistException, UserAlreadyFollowsException{
        Optional<User> followedByUserOptional = userRepository.findByUserName(followedBy);
        if(followedByUserOptional.isEmpty()) {
            throw new UserDoesNotExistException();
        }
        User followedByUser = followedByUserOptional.get();

        Optional<User> followedToUserOptional = userRepository.findByUserName(followedTo);
        if(followedToUserOptional.isEmpty()) {
            throw new UserDoesNotExistException();
        }
        User followedToUser = followedToUserOptional.get();

        Optional<Follow> followOptional = followRepository.findByFollowedByAndFollowerTo(followedByUser, followedToUser);
        if(followOptional.isPresent()) {
            throw new UserAlreadyFollowsException();
        }

        Follow follow = new Follow();
        follow.setFollowedBy(followedByUser);
        follow.setFollowerTo(followedToUser);
        return followRepository.save(follow);
    }
}
