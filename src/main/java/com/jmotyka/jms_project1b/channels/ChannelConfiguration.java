package com.jmotyka.jms_project1b.channels;

import com.jmotyka.jms_project1b.channels.domain.ports.ChannelFactory;
import com.jmotyka.jms_project1b.channels.domain.ports.ChannelRepository;
import com.jmotyka.jms_project1b.channels.domain.ports.ChannelService;
import com.jmotyka.jms_project1b.channels.domain.processors.DefaultChannelFactory;
import com.jmotyka.jms_project1b.commons.IdGenerator;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class ChannelConfiguration {

private static final ChannelFactory CHANNEL_FACTORY = new DefaultChannelFactory();

@Singleton
@Produces
public ChannelService channelService(IdGenerator idGenerator, ChannelRepository channelRepository){
    return CHANNEL_FACTORY.channelService(idGenerator, channelRepository);
}

/*    @Singleton
    @Produces
    public JpaPersistenceChannelMapper jpaPersistenceChannelMapper(){
        return Mappers.getMapper(JpaPersistenceChannelMapper.class);
    }*/

}
