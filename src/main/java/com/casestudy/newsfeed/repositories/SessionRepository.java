package com.casestudy.newsfeed.repositories;

import com.casestudy.newsfeed.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Override
    Session save(Session session);
}
