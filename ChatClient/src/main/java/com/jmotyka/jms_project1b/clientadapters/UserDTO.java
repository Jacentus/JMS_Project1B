package com.jmotyka.jms_project1b.clientadapters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.Instant;

@XmlRootElement(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO implements Serializable {

    @Getter
    String id;
    @Getter
    String userName;
    @JsonIgnore
    Instant timestamp;

    public UserDTO(String userName) {
        this.userName = userName;
    }

    public UserDTO() {
    }

    public UserDTO(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public UserDTO(String id, String userName, Instant timestamp) {
        this.id = id;
        this.userName = userName;
        this.timestamp = timestamp;
    }
}
