package com.casestudy.newsfeed.repositories;

import com.casestudy.newsfeed.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    User save(User user);
}
