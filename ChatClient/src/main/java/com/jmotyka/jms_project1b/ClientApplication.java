package com.jmotyka.jms_project1b;

import com.jmotyka.jms_project1b.GUI.GUI;
import com.jmotyka.jms_project1b.clientadapters.BinaryMapper;
import com.jmotyka.jms_project1b.clientadapters.UserDTO;

import lombok.SneakyThrows;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;


import javax.naming.NamingException;

public class ClientApplication {

    public static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    public static final String MESSAGES_TOPIC_JNDI_NAME = "jms/topic/Messages";
    @SneakyThrows
    public static void main(String[] args) throws NamingException {

        var restClient = new ResteasyClientBuilderImpl()
                .register(BinaryMapper.class) // binary mapper? Po co?
                .build();

        RestClient myClient = new RestClient(restClient);

        GUI gui = new GUI(myClient);

        UserDTO user  = gui.askForUsername();

        gui.chooseFromMenu();

        myClient.getClient().close();

    }

}