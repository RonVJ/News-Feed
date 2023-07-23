package com.casestudy.newsfeed.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment extends FeedComponent{
    @ManyToOne
    private FeedComponent parentFeedComponent;
}
