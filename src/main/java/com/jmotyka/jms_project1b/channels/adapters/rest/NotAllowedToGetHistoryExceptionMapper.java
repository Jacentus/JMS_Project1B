package com.jmotyka.jms_project1b.channels.adapters.rest;

import com.jmotyka.jms_project1b.channels.adapters.persistence.NotAllowedToGetHistoryException;
import com.jmotyka.jms_project1b.commons.ExceptionDto;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAllowedToGetHistoryExceptionMapper implements ExceptionMapper<NotAllowedToGetHistoryException>{

    @Override
    public Response toResponse(NotAllowedToGetHistoryException exception) {
        return Response.status(Response.Status.FORBIDDEN)
                .entity(new ExceptionDto("You are not allowed to view history"))
                .build();
    }

}