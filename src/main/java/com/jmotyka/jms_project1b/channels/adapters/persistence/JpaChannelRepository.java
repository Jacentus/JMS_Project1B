package com.jmotyka.jms_project1b.channels.adapters.persistence;

import com.jmotyka.jms_project1b.channels.domain.processors.NoSuchChannelException;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
@Log
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

    public Optional<List<String>>getAllPublicChannels(){
        List<String> publicChannels = entityManager.createQuery("select c.channelName from Channel c", String.class)
                .getResultList();
        return Optional.ofNullable(publicChannels);
    }

    public Optional<List<String>> getChannelHistory(String channelName){
        // TODO: VALIDATE IF PERMITTED TO SEE HISTORY
       ChannelEntity channelEntity = entityManager.createQuery("select c from Channel c JOIN FETCH c.channelHistory where c.channelName = :channelName ", ChannelEntity.class)
                .setParameter("channelName", channelName)
                .getSingleResult();
        Optional<List<String>> channelHistory = Optional.ofNullable(channelEntity.getChannelHistory());
        return channelHistory;
    }

    public void saveMessageToChannelHistory(String text, String addressee){ // chyba jednak nie działa
        Optional<ChannelEntity> channelEntity = Optional.ofNullable(getChannelByName(addressee).orElseThrow(NoSuchChannelException::new));
        channelEntity.get().getChannelHistory().add(text);
        save(channelEntity.get());
        log.info("message added to channel history");
    }

}