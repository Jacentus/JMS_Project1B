package com.jmotyka.jms_project1b.chat;

import com.jmotyka.jms_project1b.channels.domain.ports.ChannelRepository;
import com.jmotyka.jms_project1b.chat.validation.ValidateUser;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.interceptor.Interceptors;
import javax.jms.*;

@Singleton
@MessageDriven(activationConfig = {//spinamy się z Topic
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Messages") //Messages zezwoliłem na serwerze
})
@Log
public class ChatService implements MessageListener {

    @Inject
    private ChannelRepository channelRepository; // do zapisywania wiadomości

    @SneakyThrows
    @Override
   // @Interceptors(ValidateUser.class)
    public void onMessage(Message message) {
        // z properties wiadomości wyciągam kanał oraz nadawcę

        String channelName = message.getStringProperty("channel");

        String sender = message.getStringProperty("sender");

        log.info("STRING PROPERTY: " + channelName + " SENDER: " + sender);

        // weryfikuję, czy nadawca może wysyłać na dany kanał TODO
        // jeśli tak, zapisuję wiadomośc w historii i publikuję wiadomość TODO
        // jeśli nie, to nie publikuję wiadomości TODO

        ChatMessage chatMessage = message.getBody(ChatMessage.class);

        channelRepository.saveMessageToChannelHistory(chatMessage.getText(), channelName); // działa

        log.info("New message: " + chatMessage.getText());
    }

/*
    @PostConstruct
    public void postConstruct(){
        log.info(getClass().getSimpleName() + ": postConstruct");
    }

    @PreDestroy
    public void preDestroy(){
        log.info(getClass().getSimpleName() + ": preDestroy");
    }
*/


}
