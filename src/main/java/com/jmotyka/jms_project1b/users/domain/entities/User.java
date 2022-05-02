package com.jmotyka.jms_project1b.users.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
//@ValidateUser jak i gdzie dodać walidację usera, aby odpalała się przy konstruktorze?
public class User {

    private String id;
    @Setter
    private String userName;
    private Instant timestamp; // to mogłoby być jakieś wstrzykiwane metadane

    /* @ValidateUser // czy tak?
   public User(String userName) {
        this.userName = userName;
    }*/


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}