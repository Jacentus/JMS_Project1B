package com.jmotyka.jms_project1b;

import com.jmotyka.jms_project1b.commons.ChatMessage;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.NamingException;

public class Client {

    private static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    private static final String MESSAGES_TOPIC_JNDI_NAME = "jms/topic/Messages";


    public static void main(String[] args) throws NamingException {

        // łączę się do komponentu
        ProxyFactory proxyFactory = new ProxyFactory();
        ConnectionFactory connectionFactory = proxyFactory.createProxy(CONNECTION_FACTORY_JNDI_NAME);
        Topic topic = proxyFactory.createProxy(MESSAGES_TOPIC_JNDI_NAME);

        try(JMSContext jmsContext = connectionFactory.createContext()){
            jmsContext.createProducer().send(topic, new ChatMessage("HELLO, IM WORKING"));
        }



    }


}
