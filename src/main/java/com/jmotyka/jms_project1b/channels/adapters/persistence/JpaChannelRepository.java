package com.jmotyka.jms_project1b.channels.adapters.persistence;

import lombok.Setter;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Singleton
public class JpaChannelRepository {

    @Setter
    @PersistenceContext(unitName = "projectBdb")
    private EntityManager entityManager;

    public ChannelEntity save(ChannelEntity channelEntity){
        entityManager.persist(channelEntity);
        return channelEntity;
    }

    public Optional<ChannelEntity> getChannelByName(String channelName){
        return Optional.ofNullable(entityManager.find(ChannelEntity.class, channelName));
    }

}