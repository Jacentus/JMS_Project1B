package com.jmotyka.jms_project1b;

import com.jmotyka.jms_project1b.GUI.GUI;
import com.jmotyka.jms_project1b.chat.ChatMessage;
import com.jmotyka.jms_project1b.users.adapters.rest.UserDTO;
import lombok.Getter;
import lombok.SneakyThrows;
import javax.naming.NamingException;
import javax.ws.rs.client.*;

public class ClientApplication {

    public static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    public static final String MESSAGES_TOPIC_JNDI_NAME = "jms/topic/Messages";
    @SneakyThrows
    public static void main(String[] args) throws NamingException {

        Client client = ClientBuilder.newClient();
        RestClient restClient = new RestClient(client);
        GUI gui = new GUI(restClient);

        UserDTO user  = gui.askForUsername();

        gui.chooseFromMenu();

        restClient.getClient().close();

    }

}
