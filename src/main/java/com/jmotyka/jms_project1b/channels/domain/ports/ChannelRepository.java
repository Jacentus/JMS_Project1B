package com.jmotyka.jms_project1b.channels.domain.ports;

import com.jmotyka.jms_project1b.channels.domain.entities.Channel;

import java.util.Optional;

public interface ChannelRepository {

    Channel createNewChannel(Channel channel);

    Optional<Channel> getChannelByName(String channelName);

}
