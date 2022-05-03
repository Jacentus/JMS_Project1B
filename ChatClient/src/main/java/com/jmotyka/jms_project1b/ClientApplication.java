package com.jmotyka.jms_project1b;

import com.jmotyka.jms_project1b.GUI.GUI;
import com.jmotyka.jms_project1b.users.adapters.rest.UserDTO;

import lombok.SneakyThrows;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;


import javax.naming.NamingException;

public class ClientApplication {

    public static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    public static final String MESSAGES_TOPIC_JNDI_NAME = "jms/topic/Messages";
    @SneakyThrows
    public static void main(String[] args) throws NamingException {

        ResteasyClient client = new ResteasyClientBuilderImpl().build();

        //ResteasyClient client = ResteasyClientBuilder.newBuilder().build();

        RestClient restClient = new RestClient(client);

        GUI gui = new GUI(restClient);

        UserDTO user  = gui.askForUsername();

        gui.chooseFromMenu();

        restClient.getClient().close();

    }

}