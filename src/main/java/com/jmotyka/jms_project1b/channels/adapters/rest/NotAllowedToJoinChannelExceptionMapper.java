package com.jmotyka.jms_project1b.channels.adapters.rest;

import com.jmotyka.jms_project1b.channels.domain.processors.NotAllowedToJoinChannelException;
import com.jmotyka.jms_project1b.commons.ExceptionDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NotAllowedToJoinChannelExceptionMapper implements ExceptionMapper<NotAllowedToJoinChannelException> {

    @Override
    public Response toResponse(NotAllowedToJoinChannelException e) {
        return Response.status(Response.Status.CONFLICT)
                .entity(new ExceptionDto("You are not allowed to join that channel"))
                .build();
    }

}
