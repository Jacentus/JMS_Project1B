package com.jmotyka.jms_project1b;

import com.jmotyka.jms_project1b.GUI.GUI;
import com.jmotyka.jms_project1b.chat.ChatMessage;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.NamingException;
import javax.ws.rs.client.*;

public class ClientApplication {


    public static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";


    @SneakyThrows
    public static void main(String[] args) throws NamingException {

        Client client = ClientBuilder.newClient();

        RestClient restClient = new RestClient(client);

        GUI gui = new GUI(restClient);

        String username = gui.askForUsername();

        //gui.setClient(client);
        //gui.chooseFromMenu();


        restClient.getClient().close();

        /////////////////////// TODO WYSYŁANIE PRZENIEŚĆ DO CHATBOXA /////////

/*        try
                (JMSContext jmsContext = connectionFactory.createContext())
        {
            Message message = jmsContext.createObjectMessage(new ChatMessage("THIS IS MY MESSAGE"));
            message.setStringProperty("channel", "Magiczny");
            message.setStringProperty("sender", "Jacek");

            jmsContext.createProducer().send(topic, message);
        }*/

    }


}
