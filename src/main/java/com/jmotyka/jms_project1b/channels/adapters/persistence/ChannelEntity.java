package com.jmotyka.jms_project1b.channels.adapters.persistence;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.*;

@NamedQuery(name = ChannelEntity.GET_BY_CHANNELNAME, query = "select c from Channel c where c.channelName = :channelName")
@Entity(name = "Channel")
@EqualsAndHashCode(of = "channelName")
@Data
public class ChannelEntity {

    public static final String GET_BY_CHANNELNAME = "channelGetByName";

    private String id;
    @Id
    private String channelName;
    private boolean isPrivate;

    @ElementCollection
    private Set<String> permittedUsers = new HashSet<>();

    @ElementCollection
    private List<String> channelHistory = new ArrayList<>();

}