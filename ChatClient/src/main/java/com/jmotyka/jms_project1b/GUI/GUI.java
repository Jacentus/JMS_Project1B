package com.jmotyka.jms_project1b.GUI;

import com.jmotyka.jms_project1b.FileConverter;
import com.jmotyka.jms_project1b.RestClient;
import com.jmotyka.jms_project1b.commons.ExceptionDto;
import com.jmotyka.jms_project1b.users.adapters.rest.UserDTO;
import com.jmotyka.jms_project1b.users.domain.processors.NoSuchUserException;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

public class GUI {

    private final FileConverter fileConverter = new FileConverter();

    @Getter
    @Setter
    private RestClient client;
    @Getter
    @Setter
    private UserDTO user;

    public GUI(RestClient client) {
        this.client = client;
    }

    public void printMenu() {
        System.out.println("***** CHAT APP *****");
        System.out.println("[1] show open channels [2] join channel");
        System.out.println("[3] create private channel [4] join private channel [5] download message history");
    }

    public UserDTO askForUsername() { //TODO: CREATE/GET USER DETAILS FROM DATABASE
        System.out.println("Enter username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
       // try{
        //UserDTO user = client.getUserByName(username);
        //return user;
       // } catch (Exception e){
           // UserDTO user = client.createNewUser(username);
            UserDTO user = new UserDTO(username); //TODO: IDENTIFY USERS BY ID
            this.setUser(user);
            return user;
        //}
    }

    public void chooseFromMenu() throws InterruptedException {
        while (true) {
            printMenu();
            String choice;
            System.out.print("Your choice: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    client.getAllPublicChannels(); //TODO: IMPLEMENT REST CLIENT REQUEST
        /*            client.getLock().getServerResponseLock().lock();
                    try {
                        client.sendRequest(new GetAllChannelsRequest(client.getUsername(), Request.RequestType.GET_ALL_CHANNELS));
                        client.getLock().getResponseHandled().await();
                    } finally {
                        client.getLock().getServerResponseLock().unlock();
                    }*/
                    break;
                case "2":
                    System.out.println("Type channel name: ");
                    String channelName = scanner.nextLine();

                    //TODO: ADD USER TO PERMITTED USERS OF PUBLIC CHANNEL;

                    //client.getLock().getServerResponseLock().lock();
                    try {
                        //client.sendRequest(new JoinPublicChannelRequest(client.getUsername(), Request.RequestType.JOIN_PUBLIC_CHANNEL, channelName));
                        //client.getLock().getResponseHandled().await();
                    } finally {
                        //client.getLock().getServerResponseLock().unlock();
                    }
                    ChatBox publicChatBox = new ChatBox(scanner, fileConverter, channelName, user);
                    publicChatBox.launchChatBox();
                    //client.sendRequest(new RemoveFromChannelRequest(client.getUsername(), Request.RequestType.REMOVE_FROM_CHANNEL, channelName));*/
                    break;
                case "3":
                    System.out.println("Type channel name: ");
                   // String newPrivateChannelName = scanner.nextLine();
                    System.out.println("Provide list of users you want to chat with, one by one.");
                    System.out.println("Type #DONE when finished: ");
                    String permittedUser = null;
                  /*  ArrayList<String> permittedUsers = new ArrayList<>();
                    permittedUsers.add(client.getUsername());
                    while (true) {
                        permittedUser = scanner.nextLine();
                        if (permittedUser.equalsIgnoreCase("#DONE")) {
                            break;
                        }
                        permittedUsers.add(permittedUser);
                    }
                    client.getLock().getServerResponseLock().lock();
                    try {
                        client.sendRequest(new CreatePrivateChannelRequest(client.getUsername(), newPrivateChannelName, Request.RequestType.CREATE_NEW_PRIVATE_CHANNEL, true, permittedUsers));
                        client.getLock().getResponseHandled().await();
                    } finally {
                        client.getLock().getServerResponseLock().unlock();
                    }
                    if (client.getIsPermittedToChat()) {
                        ChatBox creatorChatBox = new ChatBox(scanner, fileConverter, client, newPrivateChannelName);
                        creatorChatBox.launchChatBox();
                        client.sendRequest(new RemoveFromChannelRequest(client.getUsername(), Request.RequestType.REMOVE_FROM_CHANNEL, newPrivateChannelName));
                    }*/
                    break;
                case "4":
                    System.out.println("Type channel name you want to join: ");
        /*            String privateChannelName = scanner.nextLine();
                    client.getLock().getServerResponseLock().lock();
                    try {
                        client.sendRequest(new JoinPrivateChannelRequest(client.getUsername(), Request.RequestType.JOIN_PRIVATE_CHANNEL, privateChannelName));
                        client.getLock().getResponseHandled().await();
                    } finally {
                        client.getLock().getServerResponseLock().unlock();
                    }
                    if (client.getIsPermittedToChat()) {
                        ChatBox joinerChatBox = new ChatBox(scanner, fileConverter, client, privateChannelName);
                        joinerChatBox.launchChatBox();
                        client.sendRequest(new RemoveFromChannelRequest(client.getUsername(), Request.RequestType.REMOVE_FROM_CHANNEL, privateChannelName));
                    }*/
                    break;
                case "5":
                    System.out.println("Type name of channel you wish to get history from: ");
                    String historicChannelName = scanner.nextLine();
                    //TODO: REST CLIENT REQUEST

                    /* client.getLock().getServerResponseLock().lock();
                    try {
                        client.sendRequest(new GetChannelHistoryRequest(client.getUsername(), historicChannelName, Request.RequestType.GET_CHANNEL_HISTORY));
                        client.getLock().getResponseHandled().await();
                    } finally {
                        client.getLock().getServerResponseLock().unlock();
                    }*/
            }
            choice = null;
        }
    }

}