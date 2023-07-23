package com.casestudy.newsfeed.services;

import com.casestudy.newsfeed.exceptions.UserDoesNotExistException;
import com.casestudy.newsfeed.models.Session;
import com.casestudy.newsfeed.models.SessionStatus;
import com.casestudy.newsfeed.models.User;
import com.casestudy.newsfeed.repositories.SessionRepository;
import com.casestudy.newsfeed.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@Service
public class SessionService {
    SessionRepository sessionRepository;
    UserRepository userRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    public Session createSession(Long userId) throws UserDoesNotExistException{
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new UserDoesNotExistException();
        }
        User user = userOptional.get();
        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setCreatedBy(user);
        return sessionRepository.save(session);
    }

}
