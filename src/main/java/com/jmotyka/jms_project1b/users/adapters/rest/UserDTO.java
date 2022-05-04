package com.jmotyka.jms_project1b.users.adapters.rest;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbAnnotation;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@NoArgsConstructor
public class UserDTO {

    private String id;
    @NotNull
    private String userName;

    private Instant timestamp;

    public UserDTO(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", createdOn=" + timestamp +
                '}';
    }

}