package com.casestudy.newsfeed.services;

import com.casestudy.newsfeed.exceptions.FeedComponentAlreadyDownVoted;
import com.casestudy.newsfeed.exceptions.FeedComponentAlreadyUpVoted;
import com.casestudy.newsfeed.exceptions.FeedComponentDoesNotExist;
import com.casestudy.newsfeed.exceptions.UserDoesNotExistException;
import com.casestudy.newsfeed.models.FeedComponent;
import com.casestudy.newsfeed.models.User;
import com.casestudy.newsfeed.models.UserFeedComponent;
import com.casestudy.newsfeed.models.VoteType;
import com.casestudy.newsfeed.repositories.FeedComponentRepository;
import com.casestudy.newsfeed.repositories.UserFeedComponentRepository;
import com.casestudy.newsfeed.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@Service
public class UserFeedComponentService {
    private UserFeedComponentRepository userFeedComponentRepository;
    private UserRepository userRepository;
    private FeedComponentRepository feedComponentRepository;

    @Autowired
    public UserFeedComponentService(UserFeedComponentRepository userFeedComponentRepository
            , UserRepository userRepository
            , FeedComponentRepository feedComponentRepository) {
        this.userFeedComponentRepository = userFeedComponentRepository;
        this.userRepository = userRepository;
        this.feedComponentRepository = feedComponentRepository;
    }

    public UserFeedComponent upVote(Long userId, Long feedComponentId) throws UserDoesNotExistException, FeedComponentDoesNotExist, FeedComponentAlreadyUpVoted {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new UserDoesNotExistException();
        }
        User user = userOptional.get();

        Optional<FeedComponent> feedComponentOptional = feedComponentRepository.findById(feedComponentId);
        if(feedComponentOptional.isEmpty()) {
            throw new FeedComponentDoesNotExist();
        }
        FeedComponent feedComponent = feedComponentOptional.get();

        Optional<UserFeedComponent> userFeedComponentOptional = userFeedComponentRepository.findByUserAndFeedComponent(user, feedComponent);

        if(!userFeedComponentOptional.isEmpty()) {
            UserFeedComponent userFeedComponent = userFeedComponentOptional.get();
            if(userFeedComponent.getVoteType().equals(VoteType.UPVOTE)) {
                throw new FeedComponentAlreadyUpVoted();
            }
            else if(userFeedComponent.getVoteType().equals(VoteType.DOWNVOTE)) {
                userFeedComponent.setVoteType(VoteType.UPVOTE);
                return userFeedComponentRepository.save(userFeedComponent);
            }
        }

        UserFeedComponent userFeedComponent = new UserFeedComponent();
        userFeedComponent.setUser(user);
        userFeedComponent.setFeedComponent(feedComponent);
        userFeedComponent.setVoteType(VoteType.UPVOTE);

        return userFeedComponentRepository.save(userFeedComponent);
    }

    public UserFeedComponent downVote(Long userId, Long feedComponentId) throws UserDoesNotExistException, FeedComponentDoesNotExist, FeedComponentAlreadyDownVoted {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new UserDoesNotExistException();
        }
        User user = userOptional.get();

        Optional<FeedComponent> feedComponentOptional = feedComponentRepository.findById(feedComponentId);
        if(feedComponentOptional.isEmpty()) {
            throw new FeedComponentDoesNotExist();
        }
        FeedComponent feedComponent = feedComponentOptional.get();

        Optional<UserFeedComponent> userFeedComponentOptional = userFeedComponentRepository.findByUserAndFeedComponent(user, feedComponent);

        if(!userFeedComponentOptional.isEmpty()) {
            UserFeedComponent userFeedComponent = userFeedComponentOptional.get();
            if(userFeedComponent.getVoteType().equals(VoteType.DOWNVOTE)) {
                throw new FeedComponentAlreadyDownVoted();
            }
            else if(userFeedComponent.getVoteType().equals(VoteType.UPVOTE)) {
                userFeedComponent.setVoteType(VoteType.DOWNVOTE);
                return userFeedComponentRepository.save(userFeedComponent);
            }
        }

        UserFeedComponent userFeedComponent = new UserFeedComponent();
        userFeedComponent.setUser(user);
        userFeedComponent.setFeedComponent(feedComponent);
        userFeedComponent.setVoteType(VoteType.DOWNVOTE);

        return userFeedComponentRepository.save(userFeedComponent);
    }
}
