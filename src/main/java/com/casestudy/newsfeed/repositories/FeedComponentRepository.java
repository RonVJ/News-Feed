package com.casestudy.newsfeed.repositories;

import com.casestudy.newsfeed.models.FeedComponent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FeedComponentRepository extends JpaRepository<FeedComponent, Long> {
    @Override
    Optional<FeedComponent> findById(Long aLong);
}
