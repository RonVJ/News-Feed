package com.casestudy.newsfeed.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class FeedComponent extends BaseModel{
    private String description;

    @ManyToOne
    private User author;
}
