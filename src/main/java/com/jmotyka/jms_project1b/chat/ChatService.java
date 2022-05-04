package com.jmotyka.jms_project1b.chat;

import com.jmotyka.jms_project1b.channels.domain.ports.ChannelRepository;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jms.Message;
import javax.jms.MessageListener;

@Singleton
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Messages")
})
@Log
public class ChatService implements MessageListener {

    @Inject
    private ChannelRepository channelRepository;

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        //log.info("TIMESTAMP MESSAGE SERVER: " + Instant.now());
        String channelName = message.getStringProperty("channel");
        String sender = message.getStringProperty("sender");
        log.info("CHANNEL " + channelName + ", SENDER: " + sender);
        channelRepository.addUserToPermittedUsers(channelName, sender);
        ChatMessage chatMessage = message.getBody(ChatMessage.class);
        channelRepository.saveMessageToChannelHistory(chatMessage.toString(), channelName);
        // wyświetlam wiadomość na serwerze
        log.info("MESSAGE: " + chatMessage.getText());
    }

}
