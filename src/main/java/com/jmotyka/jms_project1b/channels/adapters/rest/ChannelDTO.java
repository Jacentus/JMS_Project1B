package com.jmotyka.jms_project1b.channels.adapters.rest;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class ChannelDTO {

    private String id;
    @NotNull
    private String channelName;
    private Boolean isPrivate;
    private String password;
    private List<String> permittedUsers;
    private LinkedList<String> channelHistory;

    public ChannelDTO(String channelName, Boolean isPrivate, String password, List<String> permittedUsers) {
        this.channelName = channelName;
        this.isPrivate = isPrivate;
        this.password = password;
        this.permittedUsers = permittedUsers;
    }

    public ChannelDTO(String channelName, Boolean isPrivate) {
        this.channelName = channelName;
        this.isPrivate = isPrivate;
    }

}
