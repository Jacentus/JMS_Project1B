package com.jmotyka.jms_project1b.chat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage implements Serializable {

    private String addressee;

    private String text;

    private byte[] file;

    private Instant timestamp = Instant.now();

    public ChatMessage(String addressee, String text) {
        this.addressee = addressee;
        this.text = text;
    }

    public ChatMessage(String text){
        this.text = text;
    }

    public ChatMessage(byte[] file) {
        this.file = file;
    }

}
