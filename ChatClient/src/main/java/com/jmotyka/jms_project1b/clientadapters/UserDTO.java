package com.jmotyka.jms_project1b.clientadapters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.Instant;

@XmlRootElement(name = "user")
@Data
public class UserDTO implements Serializable {

    String id;
    String userName;
    @JsonIgnore
    Instant timestamp;

    public UserDTO(String userName) {
        this.userName = userName;
    }

}
