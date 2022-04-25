package com.jmotyka.jms_project1b.channels.adapters.persistence;

import com.jmotyka.jms_project1b.channels.domain.entities.Channel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface JpaPersistenceChannelMapper {

    ChannelEntity toEntity(Channel channel);

    Channel toDomain(ChannelEntity entity);


}
