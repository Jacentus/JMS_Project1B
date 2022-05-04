package com.jmotyka.jms_project1b.GUI;

import com.jmotyka.jms_project1b.FileConverter;
import com.jmotyka.jms_project1b.JmsListener;
import com.jmotyka.jms_project1b.ProxyFactory;
import com.jmotyka.jms_project1b.chat.ChatMessage;
import com.jmotyka.jms_project1b.clientadapters.UserDTO;

import javax.jms.*;
import javax.naming.NamingException;
import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.jmotyka.jms_project1b.ClientApplication.CONNECTION_FACTORY_JNDI_NAME;
import static com.jmotyka.jms_project1b.ClientApplication.MESSAGES_TOPIC_JNDI_NAME;

public class ChatBox {

    protected final Logger logger = Logger.getLogger(getClass().getName());
    protected Scanner scanner;
    protected FileConverter fileConverter;
    private final String channelName;
    private UserDTO user;
    private ProxyFactory proxyFactory;

    private ConnectionFactory connectionFactory;

    private Topic topic;

    public ChatBox(Scanner scanner, FileConverter fileConverter, String channelName, UserDTO user) {
        this.scanner = scanner;
        this.fileConverter = fileConverter;
        this.channelName = channelName;
        this.user = user;
        try {
            this.proxyFactory = new ProxyFactory();
            this.connectionFactory = proxyFactory.createProxy(CONNECTION_FACTORY_JNDI_NAME);
            this.topic = proxyFactory.createProxy(MESSAGES_TOPIC_JNDI_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void launchChatBox() {
        System.out.println("** START CHATTING **");
        System.out.println("** TYPE #EXIT TO QUIT, TYPE #FILE TO SEND A FILE **");
        String text;
        JmsListener messageListener = new JmsListener(channelName, user, topic, connectionFactory);
        messageListener.listen();
        while (true) {
            text = scanner.nextLine();
            if (text.equalsIgnoreCase("#EXIT")) {
                System.out.println("Exiting chatroom...");
                messageListener.setExit(true);  //TODO: NOT WORKING
                messageListener.getConsumer().close();
                break;
            }
            if (text.equalsIgnoreCase("#FILE")) {
                System.out.println("TYPE PATH TO FILE: (eg. D:\\file.txt)");
                String path = scanner.nextLine();
                File file = new File(path);
                sendFile(file);
                text = null;
            } else {
                sendMessage(text);
            }
        }
    }

    private void sendMessage(String text) {
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            Message message = jmsContext.createObjectMessage(new ChatMessage(user.getUserName(), channelName ,text));
            message.setStringProperty("channel", channelName);
            message.setStringProperty("sender", user.getUserName()); //TODO: identify senders
            jmsContext.createProducer().send(topic, message);
        } catch (JMSException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "ERROR WHILE SENDING TEXT MESSAGE");
        }
        logger.log(Level.INFO, "Message successfully send");
    }

    private void sendFile(File file) {
        byte[] bytes = fileConverter.transformIntoBytes(file);
        String fileName = file.getName();
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            Message message = jmsContext.createObjectMessage(new ChatMessage(bytes, fileName));
            message.setStringProperty("channel", channelName);
            message.setStringProperty("sender", user.getUserName());// TODO: identify senders
            jmsContext.createProducer().send(topic, message);
        } catch (JMSException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "ERROR WHILE SENDING FILE");
        }
        logger.log(Level.INFO, "File send");

    }
}
