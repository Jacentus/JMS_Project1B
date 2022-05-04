package com.jmotyka.jms_project1b.clientadapters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "channel")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class ChannelDTO implements Serializable {

    private String channelName;
    private Boolean isPrivate;
    private String password;
    private List<String> permittedUsers;

    public ChannelDTO(String channelName, Boolean isPrivate, String password, List<String> permittedUsers) {
        this.channelName = channelName;
        this.isPrivate = isPrivate;
        this.password = password;
        this.permittedUsers = permittedUsers;
    }

    public ChannelDTO() {
    }

    public ChannelDTO(String channelName, Boolean isPrivate) {
        this.channelName = channelName;
        this.isPrivate = isPrivate;
    }

}