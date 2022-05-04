package com.jmotyka.jms_project1b.GUI;

import com.jmotyka.jms_project1b.FileConverter;
import com.jmotyka.jms_project1b.RestClient;
import com.jmotyka.jms_project1b.clientadapters.ChannelDTO;
import com.jmotyka.jms_project1b.clientadapters.UserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.InternalServerErrorException;
import java.util.ArrayList;
import java.util.List;
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

    public UserDTO askForUsername() {
        System.out.println("Enter username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Checking if user already exists...");
        try {
            UserDTO user = client.getUserByName(username);
            System.out.println("User read from database. Welcome back, " + user.getUserName());
            this.setUser(user);
            return user;
        } catch (InternalServerErrorException exception) {
            UserDTO user = client.createNewUser(username);
            System.out.println("New user has been created. Nice to meet you, " + user.getUserName());
            this.setUser(user);
            return user;
        } //TODO: IDENTIFY USERS BY ID
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
                    List<String> publicChannels = client.getAllPublicChannels();
                    for (String channelName : publicChannels) {
                        System.out.println(channelName);
                    }
                    //TODO: obsługa błędów
                    break;
                case "2":
                    System.out.println("Type channel name: "); // TODO: OBSŁUGA BŁĘDU GDY BRAK KANAŁU
                    String channelName = scanner.nextLine();
                    if(!client.channelIsPrivate(channelName)) {
                        ChatBox publicChatBox = new ChatBox(scanner, fileConverter, channelName, user);
                        publicChatBox.launchChatBox();
                    } else {
                        System.out.println("Type password: ");
                        String password = scanner.nextLine();
                        if(client.userPermittedToJoinPrivateChannel(channelName, password, user.getUserName())){
                            ChatBox publicChatBox = new ChatBox(scanner, fileConverter, channelName, user);
                            publicChatBox.launchChatBox();
                        } else {
                            System.out.println("You are not permitted to join that channel or it does not exist");
                        }
                        //TODO: OBSŁUGA BŁĘDÓW
                    }
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
                    int responsePrivate = client.createPrivateChannel(privateChannelDTO);
                    if(responsePrivate==201) {
                        System.out.println("A private channel has been successfully created!  You can join it now!");
                    } else System.out.println("Sth went wrong! Try again");
                    //TODO: OBSŁUGA BŁĘDÓW
                    break;
                case "4":
                    System.out.println("Type channel name: ");
                    String publicChannelName = scanner.nextLine();
                    ChannelDTO publicChannelDTO = new ChannelDTO(publicChannelName, false);
                    int response = client.createPublicChannel(publicChannelDTO);
                    if(response==201) {
                        System.out.println("A public channel has been successfully created! You can join it now!");
                    } else System.out.println("Sth went wrong! Try again");
                    break;
                case "5": // działa. TODO: ZROBIĆ OBSŁUGĘ BŁĘDÓW
                    System.out.println("Type name of channel you wish to get history from: ");
                    String historicChannelName = scanner.nextLine();
                    List<String> history = client.getChannelHistory(historicChannelName, user.getUserName());
                    for (String message: history) {
                        System.out.println(message);
                    }
            }
            choice = null;
        }

    }

}