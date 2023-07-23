package com.casestudy.newsfeed.repositories;

import com.casestudy.newsfeed.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Override
    Optional<Comment> findById(Long aLong);

    Comment save(Comment comment);
}
