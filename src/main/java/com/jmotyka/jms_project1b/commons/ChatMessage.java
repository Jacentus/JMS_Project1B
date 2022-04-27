package com.jmotyka.jms_project1b.commons;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ChatMessage implements Serializable {

    private String text;

    private byte[] file;

    public ChatMessage(String text) {
        this.text = text;
    }

    public ChatMessage(byte[] file) {
        this.file = file;
    }
}
