package com.casestudy.newsfeed.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public class Follow extends BaseModel{
    @ManyToOne
    private User followerTo;

    @ManyToOne
    private User followedBy;
}
