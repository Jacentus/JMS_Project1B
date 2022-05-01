package com.jmotyka.jms_project1b;

import com.jmotyka.jms_project1b.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import javax.jms.*;
import javax.naming.NamingException;
import java.util.Scanner;

@Log
@AllArgsConstructor
public class JmsListener implements Runnable {

    public static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    public static final String MESSAGES_TOPIC_JNDI_NAME = "jms/topic/Messages";

    private String channelName;

    private String userName;

    public JmsListener(String channelName) {
        this.channelName = channelName;
    }

    private static MessageListener onMessage = message -> {
        try {
            log.info(message.getBody(ChatMessage.class).getTimestamp().toString());
            log.info(message.getBody(ChatMessage.class).getText());
            //TODO: CHECK IF FILE HAS BEEN SEND
        } catch (JMSException e) {
            e.printStackTrace();
        }
    };

    @SneakyThrows
    @Override
    public void run() {
            ProxyFactory proxyFactory = new ProxyFactory();
            ConnectionFactory connectionFactory = proxyFactory.createProxy(CONNECTION_FACTORY_JNDI_NAME);
            Topic topic = proxyFactory.createProxy(MESSAGES_TOPIC_JNDI_NAME);
            try (JMSContext jmsContext = connectionFactory.createContext();
             JMSConsumer consumer = jmsContext
                     .createConsumer(topic, "channel = '" + channelName + "'")) {
            consumer.setMessageListener(onMessage);
            new Scanner(System.in).next();
        }

    }


    public static void main(String[] args) throws NamingException {
        var proxyFactory = new ProxyFactory();
        ConnectionFactory connectionFactory = proxyFactory.createProxy(CONNECTION_FACTORY_JNDI_NAME);
        Topic topic = proxyFactory.createProxy(MESSAGES_TOPIC_JNDI_NAME);
        try (JMSContext jmsContext = connectionFactory.createContext();

             JMSConsumer consumer = jmsContext
                     .createConsumer(topic, "channel = 'Magiczny'")) {
            //jmsContext.setClientID("Jacek");
            consumer.setMessageListener(onMessage);
            new Scanner(System.in).next();
        }
    }


}