package com.jmotyka.jms_project1b.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage implements Serializable {

    private String channelName;
    private String sender;
    private String text;
    private byte[] file;

    private String fileName;

    private LocalDateTime timestamp = LocalDateTime.now();

    public ChatMessage(String sender, String channelName, String text) {
        this.sender = sender;
        this.channelName = channelName;
        this.text = text;
    }

    public ChatMessage(String text){
        this.text = text;
    }

    public ChatMessage(String sender, String text){
        this.text = text;
    }

    public ChatMessage(byte[] file, String fileName) {
        this.file = file;
        this.fileName = fileName;
    }

    public ChatMessage(String sender, String text, byte[] file, String fileName) {
        this.sender = sender;
        this.text = text;
        this.file = file;
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "| " + sender + " | " + timestamp + " | " + text;
    }

}
