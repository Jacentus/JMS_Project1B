package com.jmotyka.jms_project1b.channels.domain.ports;

import com.jmotyka.jms_project1b.commons.IdGenerator;

public interface ChannelFactory {

    ChannelService channelService(IdGenerator idGenerator, ChannelRepository channelRepository);

}