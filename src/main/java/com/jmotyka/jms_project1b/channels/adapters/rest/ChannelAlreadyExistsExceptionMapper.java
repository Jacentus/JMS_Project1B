package com.jmotyka.jms_project1b.channels.adapters.rest;

import com.jmotyka.jms_project1b.channels.domain.processors.ChannelAlreadyExistsException;
import com.jmotyka.jms_project1b.commons.ExceptionDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ChannelAlreadyExistsExceptionMapper implements ExceptionMapper<ChannelAlreadyExistsException> {

    @Override
    public Response toResponse(ChannelAlreadyExistsException e) {
        return Response.status(Response.Status.CONFLICT)
                .entity(new ExceptionDto("Such channel already exists"))
                .build();
    }

}