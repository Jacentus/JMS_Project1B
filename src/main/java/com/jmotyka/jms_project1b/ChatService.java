package com.jmotyka.jms_project1b;

import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;

@MessageDriven(activationConfig = {//spinamy się z Topic
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Messages") //Messages zezwoliłem na serwerze
})
@Log
public class ChatService implements MessageListener {

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        String text = message.getBody(String.class);
        log.info("New message: " + text);
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
