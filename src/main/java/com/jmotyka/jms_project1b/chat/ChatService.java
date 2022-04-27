package com.jmotyka.jms_project1b.chat;

import com.jmotyka.jms_project1b.channels.domain.entities.Channel;
import com.jmotyka.jms_project1b.commons.ChatMessage;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Singleton;
import javax.jms.*;

@Singleton
@MessageDriven(activationConfig = {//spinamy się z Topic
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Messages") //Messages zezwoliłem na serwerze
})
@Log
public class ChatService implements MessageListener {

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        ChatMessage chatMessage = message.getBody(ChatMessage.class);
        log.info("New message: " + chatMessage.getText());
    }

    @PostConstruct
    public void postConstruct(){
        log.info(getClass().getSimpleName() + ": postConstruct");
    }

    @PreDestroy
    public void preDestroy(){
        log.info(getClass().getSimpleName() + ": preDestroy");
    }




}
