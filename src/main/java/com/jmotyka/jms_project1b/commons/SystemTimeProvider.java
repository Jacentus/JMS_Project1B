package com.jmotyka.jms_project1b.commons;

import javax.inject.Singleton;
import java.time.Instant;

@Singleton
public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }
}
