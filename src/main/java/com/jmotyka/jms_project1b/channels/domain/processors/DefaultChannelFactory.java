package com.jmotyka.jms_project1b.channels.domain.processors;

import com.jmotyka.jms_project1b.channels.domain.ports.ChannelFactory;
import com.jmotyka.jms_project1b.channels.domain.ports.ChannelRepository;
import com.jmotyka.jms_project1b.channels.domain.ports.ChannelService;
import com.jmotyka.jms_project1b.commons.IdGenerator;

public class DefaultChannelFactory implements ChannelFactory {

    @Override
    public ChannelService channelService(IdGenerator idGenerator, ChannelRepository channelRepository) {
        return new ChannelProcessor(idGenerator, channelRepository);
    }
}