package com.casestudy.newsfeed.repositories;

import com.casestudy.newsfeed.models.FeedComponent;
import com.casestudy.newsfeed.models.User;
import com.casestudy.newsfeed.models.UserFeedComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserFeedComponentRepository extends JpaRepository<UserFeedComponent, Long> {
    @Override
    UserFeedComponent save(UserFeedComponent userFeedComponent);

    Optional<UserFeedComponent> findByUserAndFeedComponent(User user, FeedComponent feedComponent);
}
