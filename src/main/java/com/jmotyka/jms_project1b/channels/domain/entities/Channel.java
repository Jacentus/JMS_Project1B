package com.jmotyka.jms_project1b.channels.domain.entities;

import lombok.Builder;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Builder
@Getter
public class Channel {

    private String id;
    private final String channelName;
    private final boolean isPrivate;
    private List<String> permittedUsers;
    private LinkedList<String> channelHistory; // zsynchronizować


}
