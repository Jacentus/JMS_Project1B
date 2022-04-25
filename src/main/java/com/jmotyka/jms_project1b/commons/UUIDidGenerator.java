package com.jmotyka.jms_project1b.commons;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class UUIDidGenerator implements IdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }

}
