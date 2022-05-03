package com.jmotyka.jms_project1b;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.json.Json;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.jmotyka.jms_project1b.users.adapters.rest.UserDTO;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.ArrayList;
import java.util.List;

@Log
public class RestClient {

    @Getter
    private ResteasyClient client;
    @Getter
    @Setter
    private UserDTO user;
    private final String path = "http://localhost:8080/JMS_Project1B-1.0-SNAPSHOT/api";

    public RestClient(ResteasyClient client) {
        this.client = client;
    }

    //GET USER BY USERNAME
    public UserDTO getUserByName(String userName) {
        ResteasyWebTarget target = client.target(path);
        UserServicesClient userProxy = target.proxy(UserServicesClient.class);
        UserDTO userDTO = userProxy.getByUsername(userName);
        log.info("USER DTO: " + userDTO);
        return userDTO;
    }
}
    //CREATE NEW USER
   /* public UserDTO createNewUser(String username) {
        ResteasyWebTarget target = client.target(path);
        UserServicesClient userProxy = target.proxy(UserServicesClient.class);
    }*/


/*


                        ("http://localhost:8080/JMS_Project1B-1.0-SNAPSHOT/api/users")
                .path("{username}").resolveTemplate("username", username);
        Response response = resource.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(username));
        log.info("RESPONSE FROM SERVER: " + response);
        UserDTO userDTO = response.readEntity(UserDTO.class);
        //UserDTO userDTO = new Gson().fromJson(String.valueOf(response), UserDTO.class);
        System.out.println("TO JEST MÓJ USERDTO OD SERWERA Z CREATE NEW USER: " + userDTO);
        return userDTO;
    }
}



    //// dalsze metody

    public String getChannelHistory(String channelName, String userName){
        WebTarget webTarget = client.target("http://localhost:8080/JMS_Project1B-1.0-SNAPSHOT/api/channels/{channelName}/{userName}")
                .resolveTemplate("channelName", channelName).resolveTemplate("userName", userName);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON_TYPE);
        Response response = invocationBuilder.get();
        Json json = response.readEntity(Json.class);
        System.out.println("RESPONSE: " + response);
        System.out.println("RESPONSE CLASS: " + response.getClass());
        System.out.println("RESPONSE ENTITY: " + response.getEntity());
        System.out.println("JSON: " + json);
        System.out.println("RESPONSE ENTITY CLASS: " + response.getEntity().getClass());
        return "what's up";
    }

    public List<String> getAllPublicChannels(){
        WebTarget resource = client.target("http://localhost:8080/JMS_Project1B-1.0-SNAPSHOT/api/channels");
        Response response = resource.request(MediaType.APPLICATION_JSON_TYPE).get();
        List<String> listOfAllChannels = new Gson().fromJson(String.valueOf(response), ArrayList.class);
        log.info("RESPONSE FROM SERVER: " + listOfAllChannels);
        return listOfAllChannels;
    }


*/