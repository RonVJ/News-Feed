package com.casestudy.newsfeed.repositories;

import com.casestudy.newsfeed.models.Follow;
import com.casestudy.newsfeed.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowedByAndFollowerTo(User followedByUser, User followedToUser);

    List<User> findAllByFollowedBy(User followedBy);

    Follow save(Follow follow);

    @Override
    void delete(Follow follow);
}
