package com.jmotyka.jms_project1b;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.json.Json;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.jmotyka.jms_project1b.users.adapters.rest.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Log
public class RestClient {

    @Getter
    private Client client;
    @Getter
    @Setter
    private UserDTO user;

    public RestClient(Client client) {
        this.client = client;
    }

    public UserDTO createNewUser(String username){
        WebTarget resource = client.target("http://localhost:8080/JMS_Project1B-1.0-SNAPSHOT/api/users")
                .path("{username}").resolveTemplate("username", username);
        Response response = resource.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(username));
        log.info("RESPONSE FROM SERVER: " + response);
        UserDTO userDTO = response.readEntity(UserDTO.class);
        //UserDTO userDTO = new Gson().fromJson(String.valueOf(response), UserDTO.class);
        System.out.println("TO JEST MÓJ USERDTO OD SERWERA Z CREATE NEW USER: " + userDTO);
        return userDTO;
    }

    public List<String> getAllPublicChannels(){
        WebTarget resource = client.target("http://localhost:8080/JMS_Project1B-1.0-SNAPSHOT/api/channels");
        Response response = resource.request(MediaType.APPLICATION_JSON_TYPE).get();
        List<String> listOfAllChannels = new Gson().fromJson(String.valueOf(response), ArrayList.class);
        log.info("RESPONSE FROM SERVER: " + listOfAllChannels);
        return listOfAllChannels;
    }

    public UserDTO getUserByName(String userName){
        WebTarget resource = client.target("http://localhost:8080/JMS_Project1B-1.0-SNAPSHOT/api/users")
                .path("{username}").resolveTemplate("username", userName);
        Response response = resource.request(MediaType.APPLICATION_JSON_TYPE).get();
        log.info("RESPONSE FROM SERVER: " + response);
        UserDTO userDTO = response.readEntity(UserDTO.class);
        //UserDTO userDTO = new Gson().fromJson(String.valueOf(response), UserDTO.class);
        System.out.println("TO JEST MÓJ USER DTO OD SERWERA Z METODY GET: " + userDTO);
        return userDTO;
    }

    //// dalsze metody

}
