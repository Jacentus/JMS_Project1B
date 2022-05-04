package com.jmotyka.jms_project1b.channels.domain.ports;

import com.jmotyka.jms_project1b.channels.adapters.persistence.NotAllowedToGetHistoryException;
import com.jmotyka.jms_project1b.channels.domain.entities.Channel;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository {

    Channel createNewChannel(Channel channel);

    Optional<Channel> getChannelByName(String channelName);

    Optional<List<String>> getChannelHistory(String channelName, String username) throws NotAllowedToGetHistoryException;

    Optional<List<String>>getAllPublicChannels();

    void saveMessageToChannelHistory(String text, String addressee);

    void addUserToPermittedUsers(String channelName, String sender);

    boolean checkIfChannelIsPrivate(String channelName);

    boolean checkIfPermittedToJoinPrivateChannel(String channelName, String password, String username);

}