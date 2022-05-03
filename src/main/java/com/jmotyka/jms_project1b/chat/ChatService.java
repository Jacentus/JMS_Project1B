package com.jmotyka.jms_project1b.chat;

import com.jmotyka.jms_project1b.channels.domain.ports.ChannelRepository;
import com.jmotyka.jms_project1b.users.domain.ports.UsersRepository;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.time.Instant;

@Singleton
@MessageDriven(activationConfig = {//spinamy się z Topic
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Messages") //Messages zezwoliłem na serwerze
})
@Log
public class ChatService implements MessageListener {

    @Inject
    private ChannelRepository channelRepository; // do zapisywania wiadomości
    @Inject
    private UsersRepository usersRepository; // TODO: CZY POTRZEBNE  do walidowania użytkowników?

    @SneakyThrows
    @Override
   // @Interceptors(ValidateUser.class)
    public void onMessage(Message message) {
        System.out.println("TIMESTAMP MESSAGE SERVER: " + Instant.now());
        // z properties wiadomości wyciągam kanał oraz nadawcę
        String channelName = message.getStringProperty("channel");
        String sender = message.getStringProperty("sender");
        log.info("CHANNEL " + channelName + ", SENDER: " + sender);
        // weryfikuję, czy nadawca może wysyłać na dany kanał TODO

        // jeśli tak, to dodaję go do listy uprawnionych userów oraz zapisuję wiadomość w historii
        channelRepository.addUserToPermittedUsers(channelName, sender);
        ChatMessage chatMessage = message.getBody(ChatMessage.class);
        channelRepository.saveMessageToChannelHistory(chatMessage.toString(), channelName); // TODO: ZAPISYWAĆ CAŁĄ WIADOMOŚĆ, RESZTA DZIAŁA
        // wyświetlam wiadomość na serwerze dla mojej informacji
        log.info("MESSAGE: " + chatMessage.getText());
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
