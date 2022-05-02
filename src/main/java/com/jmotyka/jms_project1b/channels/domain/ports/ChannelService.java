package com.jmotyka.jms_project1b.channels.domain.ports;

import com.jmotyka.jms_project1b.channels.adapters.persistence.NotAllowedToGetHistoryException;
import com.jmotyka.jms_project1b.channels.domain.entities.Channel;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

public interface ChannelService {

    Channel createChannel(String channelName, Boolean isPrivate, List<String> permittedUsers);

    Channel createChannel(String channelName);

    Optional<Channel> getChannelByName(String channelName);

    Optional<List<String>> getChannelHistory(String channelName, String username) throws NotAllowedToGetHistoryException;

    Optional<List<String>> getAllPublicChannels();

}
