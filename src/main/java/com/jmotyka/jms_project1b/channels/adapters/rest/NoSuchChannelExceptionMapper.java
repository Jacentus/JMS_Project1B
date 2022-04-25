package com.jmotyka.jms_project1b.channels.adapters.rest;

import com.jmotyka.jms_project1b.channels.domain.processors.NoSuchChannelException;
import com.jmotyka.jms_project1b.commons.ExceptionDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NoSuchChannelExceptionMapper implements ExceptionMapper<NoSuchChannelException> {

    @Override
    public Response toResponse(NoSuchChannelException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ExceptionDto("No such channel"))
                .build();
    }

}
