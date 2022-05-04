package com.jmotyka.jms_project1b.channels.adapters.rest;

import com.jmotyka.jms_project1b.channels.domain.entities.Channel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface RestChannelMapper {

    Channel toDomain(ChannelDTO dto);

    ChannelDTO toDto(Channel channel);

}