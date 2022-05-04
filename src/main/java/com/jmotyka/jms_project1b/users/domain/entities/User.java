package com.jmotyka.jms_project1b.users.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
public class User {

    private String id;
    @Setter
    private String userName;
    private Instant timestamp;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}