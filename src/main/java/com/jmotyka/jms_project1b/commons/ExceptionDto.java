package com.jmotyka.jms_project1b.commons;

import lombok.Value;

import java.time.Instant;

@Value
public class ExceptionDto {

    Instant timestamp = Instant.now();
    String description;

}