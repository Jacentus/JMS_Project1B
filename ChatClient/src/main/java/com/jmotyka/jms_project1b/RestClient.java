package com.jmotyka.jms_project1b;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.json.Json;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
public class RestClient {

    @Getter
    private Client client;

    @Getter
    @Setter
    private String username;
 //   private final WebTarget baseTarget = client.target("http://localhost:8080/JMS_Project1B-1.0-SNAPSHOT/api");

   /* private final WebTarget usersTarget = baseTarget.path("users");
    private final WebTarget channelBaseTarget = baseTarget.path("channels");

    private final WebTarget privateChannelTarget = channelBaseTarget.path("privateChannel");

    private final WebTarget publicChannelTarget = channelBaseTarget.path("publicChannel");

    private final WebTarget channelHistoryTarget = channelBaseTarget.path("history");*/

    public RestClient(Client client) {
        this.client = client;
    }

    public Response createNewUser(String username){
        WebTarget resource = client.target("http://localhost:8080/JMS_Project1B-1.0-SNAPSHOT/api/users")
                .path("{username}").resolveTemplate("username", username);
/*        Form form = new Form();
        form.param("key", "userName");
        form.param("value", username);*/

        Response response = resource.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(username));
        log.info("RESPONSE FROM SERVER: " + response);
        return response;
    }

    public Response getAllPublicChannels(){
        WebTarget resource = client.target("http://localhost:8080/JMS_Project1B-1.0-SNAPSHOT/api/channels");
        Response response = resource.request(MediaType.APPLICATION_JSON_TYPE).get();
        log.info("RESPONSE FROM SERVER: " + response.readEntity(Json.class));
        return response;
    }

    //// dalsze metody

}
