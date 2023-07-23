package com.casestudy.newsfeed.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Session extends  BaseModel{
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;

    @ManyToOne
    private User createdBy;
    private Date expiryTime;

}
