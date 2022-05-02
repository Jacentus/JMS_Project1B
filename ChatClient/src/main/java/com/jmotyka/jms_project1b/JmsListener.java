package com.jmotyka.jms_project1b;

import com.jmotyka.jms_project1b.chat.ChatMessage;
import com.jmotyka.jms_project1b.users.adapters.rest.UserDTO;
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
    private UserDTO user;
    @Setter
    private volatile boolean exit = false;

    public JmsListener(String channelName, UserDTO user, Topic topic, ConnectionFactory connectionFactory) {
        this.channelName = channelName;
        this.user = user;
        this.topic = topic;
        this.connectionFactory = connectionFactory;
    }

    private static MessageListener onMessage = message -> {
/*        if (exit){
            System.out.println("STOPPED LISTENING TO MESSAGES ON " + channelName);
        }*/
        log.info("***** MESSAGE RECEIVED ***** ");
        try {
            log.info("SENDER: " + message.getStringProperty("sender") + " MESSAGE SEND TO CHANNEL: " + message.getStringProperty("channel"));
            log.info("TIMESTAMP: " + message.getBody(ChatMessage.class).getTimestamp().toString());
            log.info("TEXT: " + message.getBody(ChatMessage.class).getText());

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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
