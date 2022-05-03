package com.jmotyka.jms_project1b.GUI;

import com.jmotyka.jms_project1b.FileConverter;
import com.jmotyka.jms_project1b.RestClient;
import com.jmotyka.jms_project1b.channels.adapters.rest.ChannelDTO;
import com.jmotyka.jms_project1b.users.adapters.rest.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
        System.out.println("[3] create private channel [4] create public channel [5] download message history");
    }

    public UserDTO askForUsername() { //TODO: CREATE/GET USER DETAILS FROM DATABASE VIA REST CLIENT
        System.out.println("Enter username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("trying to get user by rest client...");
        UserDTO user = client.getUserByName(username);
        System.out.println("SUCCESS! USER: " + user);
       // try{
        //UserDTO user = client.getUserByName(username);
        //return user;
       // } catch (Exception e){
           // UserDTO user = client.createNewUser(username);
            //UserDTO user = new UserDTO(username); //TODO: IDENTIFY USERS BY ID
            this.setUser(user);
            return user;
        //}
    }

    public void chooseFromMenu()  {
        while (true) {
            printMenu();
            String choice;
            System.out.print("Your choice: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                   // client.getAllPublicChannels();
                    //TODO: IMPLEMENT REST CLIENT REQUEST, PRINT PUBLIC CHANNELS
                    break;
                case "2":
                    System.out.println("Type channel name: ");
                    String channelName = scanner.nextLine();

                    ChatBox publicChatBox = new ChatBox(scanner, fileConverter, channelName, user);
                    //TODO: CHECK IF PUBLIC, IF SO, ALLOW, OTHERWISE ASK FOR PASSWORD
                    publicChatBox.launchChatBox();
                    break;
                case "3":
                    System.out.println("Type channel name: ");
                    String newPrivateChannelName = scanner.nextLine();
                    System.out.println("Provide list of users you want to chat with, one by one.");
                    System.out.println("Type #DONE when finished: ");
                    String permittedUser = null;
                    ArrayList<String> permittedUsers = new ArrayList<>();
                    permittedUsers.add(user.getUserName());
                    while (true) {
                        permittedUser = scanner.nextLine();
                        if (permittedUser.equalsIgnoreCase("#DONE")) {
                            break;
                        }
                        permittedUsers.add(permittedUser);
                    }
                    System.out.println("Type password needed to access private channel:");
                    String password = null;
                    password = scanner.nextLine();
                    ChannelDTO privateChannelDTO = new ChannelDTO(newPrivateChannelName, true, password, permittedUsers);
                    //TODO: SEND REQUEST, CREATE CHANNEL IF DOES NOT EXIST, OTHERWISE THROW EXCEPTION AND BREAK
                    break;
                case "4":
                    System.out.println("Type channel name: ");
                    String publicChannelName = scanner.nextLine();
                    ChannelDTO publicChannelDTO = new ChannelDTO(publicChannelName, false);
                    // TODO: SEND REQUEST, CREATE CHANNEL IF DOES NOT EXIST, OTHERWISE THROW EXCEPTION AND BREAK
                    break;
                case "5":
                    System.out.println("Type name of channel you wish to get history from: ");
                    String historicChannelName = scanner.nextLine();
                    //TODO: REST CLIENT REQUEST, PRINT CHANNEL HISTORY
            }
            choice = null;
        }
    }

}