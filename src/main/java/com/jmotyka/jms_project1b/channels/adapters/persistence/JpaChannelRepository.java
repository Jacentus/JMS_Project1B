package com.jmotyka.jms_project1b.channels.adapters.persistence;

import lombok.Setter;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
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
        ChannelEntity channelEntity = entityManager.createQuery("select c from Channel c where c.channelName = :channelName", ChannelEntity.class)
                .setParameter("channelName", channelName)
                .getSingleResult();
        return Optional.ofNullable(channelEntity);
    }

    public Optional<List<String>> getChannelHistory(String channelName){
        List<String> channelHistory = entityManager.createQuery("select c.channelHistory from Channel c where c.channelName = :channelName", String.class)
                .setParameter("channelName", channelName)
                .getResultList();
        return Optional.ofNullable(channelHistory);
    }

}