package com.jmotyka.jms_project1b.channels.domain.ports;

import com.jmotyka.jms_project1b.channels.domain.entities.Channel;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository {

    Channel createNewChannel(Channel channel);

    Optional<Channel> getChannelByName(String channelName);

    Optional<List<String>> getChannelHistory(String channelName);

    Optional<List<String>>getAllPublicChannels();

    void saveMessageToChannelHistory(String text, String addressee);

}
