package com.jmotyka.jms_project1b.channels.adapters.persistence;

import com.jmotyka.jms_project1b.channels.domain.processors.NoSuchChannelException;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        List<String> publicChannels = entityManager.createQuery("select c.channelName from Channel c where c.isPrivate = false", String.class)
                .getResultList();
        return Optional.ofNullable(publicChannels);
    }

    public Optional<List<String>> getChannelHistory(String channelName, String username) throws NotAllowedToGetHistoryException {
       ChannelEntity channelEntity = entityManager.createQuery("select c from Channel c JOIN FETCH c.channelHistory where c.channelName = :channelName ", ChannelEntity.class)
                .setParameter("channelName", channelName)
                .getSingleResult();
       if(Optional.of(channelEntity.getPermittedUsers().contains(username)).get()) {
               return Optional.ofNullable(channelEntity.getChannelHistory());
        }
       else {
            throw new NotAllowedToGetHistoryException();
        }
    }

    public void saveMessageToChannelHistory(String text, String channelName){
        Optional<ChannelEntity> channelEntity = Optional.ofNullable(getChannelByName(channelName).orElseThrow(NoSuchChannelException::new));
        channelEntity.get().getChannelHistory().add(text);
        save(channelEntity.get());
        log.info("message added to channel history");
    }

    public void addUserToPermittedUsers(String channelName, String sender) {
        Optional<ChannelEntity> channelEntity = Optional.ofNullable(getChannelByName(channelName).orElseThrow(NoSuchChannelException::new));
        if(!channelEntity.get().getPermittedUsers().contains(sender)) {
            channelEntity.get().getPermittedUsers().add(sender);
            save(channelEntity.get());
            log.info("USER ADDED TO PERMITTED USERS LIST");
        }
    }

    public boolean checkIfChannelIsPrivate(String channelName) {
        Optional<ChannelEntity> channelEntity = Optional.ofNullable(getChannelByName(channelName).orElseThrow(NoSuchChannelException::new));
        if (channelEntity.get().isPrivate()){
            return true;
        } else return false;
    }

    public boolean checkIfPermittedToJoinPrivateChannel(String channelName, String password, String username){
        Optional<ChannelEntity> channelEntity = Optional.ofNullable(getChannelByName(channelName).orElseThrow(NoSuchChannelException::new));
        System.out.println("PODANE: Channelname: " + channelName + ", password: " + password + ", username: " + username);
        System.out.println("UZYSKANE: password: " + channelEntity.get().getPassword() + ", Contains a username: " + channelEntity.get().getPermittedUsers().contains(username));
        return channelEntity.get().getPermittedUsers().contains(username) && Objects.equals(channelEntity.get().getPassword(), password);
    }

}