package com.jmotyka.jms_project1b.channels.adapters.rest;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Data
@Getter
public class ChannelDTO {

    @NotNull
    private String channelName;
    private Boolean isPrivate;
    private List<String> permittedUsers;
    private LinkedList<String> channelHistory;

}
