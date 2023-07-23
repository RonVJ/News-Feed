package com.casestudy.newsfeed.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
public class User extends BaseModel{
    private String userName;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String email;
}
