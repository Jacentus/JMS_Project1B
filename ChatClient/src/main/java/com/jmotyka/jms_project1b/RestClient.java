package com.jmotyka.jms_project1b;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import com.jmotyka.jms_project1b.clientadapters.UserDTO;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    //DZIAŁA
    public UserDTO getUserByName(String userName) {
        UserDTO user = client.target(userPath)
                .path("{userName}")
                .resolveTemplate("userName", userName)
                .request(/*MediaType.APPLICATION_JSON_TYPE*/)
                .get(UserDTO.class);
        System.out.println("dane z usera: " + user.getUserName() + ", " + user.getId());
        return user;
    }

    //DZIAŁA
    public UserDTO createNewUser(String username) {
        UserDTO user = new UserDTO(username);
        Response response = client.target(userPath)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        log.info("### Response: " + response.getStatus());
        return response.readEntity(UserDTO.class);

    }

    //GET http://localhost:8080/JMS_Project1B-1.0-SNAPSHOT/api/channels/Śmieszny
    //Content-Type: text/plain
    public boolean checkIfChannelIsPrivate(String channelName){
        boolean isPrivate = client.target(channelPath)
                .path("{channelName}")
                .resolveTemplate("channelName", channelName)
                .request(MediaType.TEXT_PLAIN)
                .get(boolean.class);
        System.out.println(isPrivate);
        return isPrivate;
    }





}
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