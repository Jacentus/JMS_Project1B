package com.jmotyka.jms_project1b;

import com.jmotyka.jms_project1b.chat.ChatMessage;
import com.jmotyka.jms_project1b.clientadapters.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import javax.jms.*;
import java.io.*;
import java.util.Objects;

@Log
@AllArgsConstructor
@Getter
public class JmsListener implements MessageListener {

    private Topic topic;
    private ConnectionFactory connectionFactory;
    private String channelName;
    private UserDTO user;
    private JMSContext jmsContext;
    private JMSConsumer consumer;
    //@Setter
    //private volatile boolean exit = false;

    public JmsListener(String channelName, UserDTO user, Topic topic, ConnectionFactory connectionFactory) {
        this.channelName = channelName;
        this.user = user;
        this.topic = topic;
        this.connectionFactory = connectionFactory;
        this.jmsContext = this.connectionFactory.createContext();
        this.jmsContext = connectionFactory.createContext();
        this.consumer = jmsContext.createConsumer(topic, "channel = '" + channelName + "'");
    }

    public void listen() {
            try {
                consumer.setMessageListener(this);
            } catch (Exception e){
                System.out.println("CLOSING MESSAGE LISTENER");
                e.printStackTrace();
            }
        }

    @Override
    public void onMessage(Message message) {
        //log.info("***** MESSAGE RECEIVED ***** ");
        try {
            if (!Objects.equals(message.getStringProperty("sender"), user.getUserName())) {
                System.out.println(message.getBody(ChatMessage.class));
                if (message.getBody(ChatMessage.class).getFile() != null) {
                String filePath = "D:\\RECEIVED_FILES\\" + message.getBody(ChatMessage.class).getFileName();
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
        }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}