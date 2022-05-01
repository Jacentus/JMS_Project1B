package com.jmotyka.jms_project1b;

import com.jmotyka.jms_project1b.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import javax.jms.*;
import java.io.*;

@Log
@AllArgsConstructor
public class JmsListener {

    private Topic topic;
    private ConnectionFactory connectionFactory;
    private String channelName;
    private String userName;
    @Setter
    private volatile boolean exit = false;

    public JmsListener(String channelName, String userName, Topic topic, ConnectionFactory connectionFactory) {
        this.channelName = channelName;
        this.userName = userName;
        this.topic = topic;
        this.connectionFactory = connectionFactory;
    }

    private static MessageListener onMessage = message -> {
        log.info("***** MESSAGE RECEIVED ***** ");
        try {
            log.info(message.getBody(ChatMessage.class).getTimestamp().toString());
            log.info(message.getBody(ChatMessage.class).getText());
            //TODO: CHECK IF FILE HAS BEEN SEND
            if(message.getBody(ChatMessage.class).getFile() != null){
                String filePath = "D:\\RECEIVED_FILES\\" + "newFile";
                System.out.println("path: " + filePath);
                File file = new File(filePath);
                try {
                    OutputStream os = new FileOutputStream(file);
                    os.write(message.getBody(ChatMessage.class).getFile());
                    os.close();
                    System.out.println("A FILE HAS BEEN SUCCESSFULLY RECEIVED");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    };

    @SneakyThrows
    public void listen() {
            try {
                JMSContext jmsContext = connectionFactory.createContext();
                JMSConsumer consumer = jmsContext.createConsumer(topic, "channel = '" + channelName + "'");
                consumer.setMessageListener(onMessage);
                if (exit){
                    consumer.close();
                    System.out.println("STOPPED LISTENING TO MESSAGES ON " + channelName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

/*    public static void main(String[] args) throws NamingException {
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
    }*/
