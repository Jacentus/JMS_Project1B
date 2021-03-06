package com.jmotyka.jms_project1b;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileConverter {

    private final Logger logger = Logger.getLogger(getClass().getName());

    public byte[] transformIntoBytes(File file) {
        byte[] byteFile = new byte[0];
        try {
            byteFile = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Reading file failed!");
            e.printStackTrace();
        }
        return byteFile;
    }

}