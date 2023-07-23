package com.casestudy.newsfeed.repositories;

import com.casestudy.newsfeed.models.FeedComponent;
import com.casestudy.newsfeed.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    Optional<Post> findById(Long aLong);

    Post save(Post post);
}
