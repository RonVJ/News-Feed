package com.casestudy.newsfeed.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserFeedComponent extends BaseModel {
    @ManyToOne
    private User user;

    @ManyToOne
    private FeedComponent feedComponent;

    @Enumerated(EnumType.ORDINAL)
    private VoteType voteType;
}
