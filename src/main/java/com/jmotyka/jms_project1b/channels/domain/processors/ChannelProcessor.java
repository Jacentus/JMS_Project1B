package com.jmotyka.jms_project1b.channels.domain.processors;

import com.jmotyka.jms_project1b.channels.domain.entities.Channel;
import com.jmotyka.jms_project1b.channels.domain.ports.ChannelRepository;
import com.jmotyka.jms_project1b.channels.domain.ports.ChannelService;
import com.jmotyka.jms_project1b.commons.IdGenerator;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ChannelProcessor implements ChannelService {

    private final IdGenerator idGenerator;
    private final ChannelRepository channelRepository;

    @Override
    public Channel createChannel(String channelName, Boolean isPrivate, List<String> permittedUsers) {
        Channel channel = Channel.builder()
                .id(idGenerator.getNext())
                .channelName(channelName)
                .isPrivate(isPrivate)
                .permittedUsers(permittedUsers)
                .build();
        return channelRepository.createNewChannel(channel);
    }

    @Override
    public Channel createChannel(String channelName) {
        Channel channel = Channel.builder()
                .id(idGenerator.getNext())
                .channelName(channelName)
                .isPrivate(false)
                .build();
        return channelRepository.createNewChannel(channel);
    }

    @Override
    public Optional<Channel> getChannelByName(String channelName) {
        return channelRepository.getChannelByName(channelName);
    }

    @Override
    public Optional<List<String>> getChannelHistory(String channelName) {
        return channelRepository.getChannelHistory(channelName);
    }

    @Override
    public Optional<List<String>> getAllPublicChannels() {
        return channelRepository.getAllPublicChannels();
    }

}
