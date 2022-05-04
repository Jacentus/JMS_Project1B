package com.jmotyka.jms_project1b;

import com.jmotyka.jms_project1b.clientadapters.ChannelDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import com.jmotyka.jms_project1b.clientadapters.UserDTO;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Log
public class RestClient {

    @Getter
    private ResteasyClient client;
    @Getter
    @Setter
    private UserDTO user;
    private final String userPath = "http://localhost:8080/JMS_Project1B-1.0-SNAPSHOT/api/users";
    private final String channelPath = "http://localhost:8080/JMS_Project1B-1.0-SNAPSHOT/api/channels";
    public RestClient(ResteasyClient client) {
        this.client = client;
    }

    //DZIAŁA  TODO: ZROBIĆ OBSŁUGĘ BŁĘDÓW
    public UserDTO getUserByName(String userName) {
        UserDTO user = client.target(userPath)
                .path("{userName}")
                .resolveTemplate("userName", userName)
                .request()
                .get(UserDTO.class);
        return user;
    }

    //DZIAŁA TODO: ZROBIĆ OBSŁUGĘ BŁĘDÓW
    public UserDTO createNewUser(String username) {
        UserDTO user = new UserDTO(username);
        Response response = client.target(userPath)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        return response.readEntity(UserDTO.class);

    }

    //DZIAŁA
    public boolean channelIsPrivate(String channelName){
        boolean isPrivate = client.target(channelPath)
                .path("{channelName}")
                .resolveTemplate("channelName", channelName)
                .request(MediaType.TEXT_PLAIN)
                .get(boolean.class);
        return isPrivate;
    }

    // DZIAŁA TODO: ZROBIĆ OBSŁUGĘ BŁĘDÓW
    public List<String> getChannelHistory(String channelName, String username) {
        Response response = client.target(channelPath+"/history")
                .path("{channelName}")
                .path("{userName}")
                .resolveTemplate("channelName", channelName)
                .resolveTemplate("userName", username)
                .request(/*MediaType.APPLICATION_JSON*/)
                .accept(MediaType.APPLICATION_JSON)
                .get();
        if(response.getStatus()==200) {
            return response.readEntity(new GenericType<List<String>>() {
            });
        } else return new ArrayList<String>();
        }

    public List<String> getAllPublicChannels() {
        Response response = client.target(channelPath)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        return response.readEntity(new GenericType<List<String>>() {
        });
    }

    public boolean userPermittedToJoinPrivateChannel(String channelName, String password, String userName){
        boolean isPermitted = client.target(channelPath)
                .path("{channelName}")
                .path("{password}")
                .path("{userName}")
                .resolveTemplate("channelName", channelName)
                .resolveTemplate("password", password)
                .resolveTemplate("userName", userName)
                .request(MediaType.TEXT_PLAIN)
                .get(boolean.class);
        return isPermitted;
    }

    public int createPublicChannel(ChannelDTO channelDTO) {
        Response response = client.target(channelPath + "/publicChannel")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(channelDTO, MediaType.APPLICATION_JSON));
        return response.getStatus();
    }

    public int createPrivateChannel(ChannelDTO channelDTO) {
        Response response = client.target(channelPath + "/privateChannel")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(channelDTO, MediaType.APPLICATION_JSON));
        return response.getStatus();
    }

}