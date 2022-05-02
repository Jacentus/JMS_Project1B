package com.jmotyka.jms_project1b.channels.adapters.persistence;

import com.jmotyka.jms_project1b.channels.domain.entities.Channel;
import com.jmotyka.jms_project1b.channels.domain.ports.ChannelRepository;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JpaChannelRepositoryAdapter implements ChannelRepository {

    private final JpaChannelRepository channelRepository;
    private final JpaPersistenceChannelMapper channelMapper;

    @Override
    public Channel createNewChannel(Channel channel) {
        ChannelEntity entity = channelMapper.toEntity(channel);
        ChannelEntity savedEntity = channelRepository.save(entity);
        return channelMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Channel> getChannelByName(String channelName) {
        return channelRepository.getChannelByName(channelName)
                .map(channelMapper::toDomain);
    }

    @Override
    public Optional<List<String>> getChannelHistory(String channelName, String username) throws NotAllowedToGetHistoryException {
        return channelRepository.getChannelHistory(channelName, username);
    }

    @Override
    public Optional<List<String>> getAllPublicChannels() {
        return channelRepository.getAllPublicChannels();
    }

    public void saveMessageToChannelHistory(String text, String addressee) { channelRepository.saveMessageToChannelHistory(text, addressee);}

}
