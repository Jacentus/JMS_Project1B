package com.jmotyka.jms_project1b.users.adapters.rest;

import com.jmotyka.jms_project1b.commons.ExceptionDto;
import com.jmotyka.jms_project1b.users.domain.processors.NoSuchUserException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoSuchUserExceptionMapper implements ExceptionMapper<NoSuchUserException> {

    @Override
    public Response toResponse(NoSuchUserException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ExceptionDto("No such user"))
                .build();
    }
}
