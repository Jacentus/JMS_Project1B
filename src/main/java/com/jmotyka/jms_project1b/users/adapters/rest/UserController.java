package com.jmotyka.jms_project1b.users.adapters.rest;

import com.jmotyka.jms_project1b.users.domain.entities.User;
import com.jmotyka.jms_project1b.users.domain.ports.UsersService;
import com.jmotyka.jms_project1b.users.domain.processors.NoSuchUserException;
import lombok.Setter;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("users")
@Setter
public class UserController {

    //@Proxy
    @Inject
    private UsersService usersService;
    @Inject
    private RestUserMapper userMapper;
    @Context
    private UriInfo uriInfo;

    @POST
    public Response createUser(UserDTO userDTO){ // czy to działa?
        User user = usersService.createUser(userMapper.toDomain(userDTO).getUserName());
        System.out.println("USER z Restcontroller: " + user);
        return Response.created(getLocation(user.getId())).build();
    }

    @GET
    @Path("{username}")
    public Response getByUsername(@PathParam("username")String username){
        User user = usersService.getByUsername(username).orElseThrow(NoSuchUserException::new);
        UserDTO userDto = userMapper.toDto(user);
        return Response.ok(userDto).build();
    }

    private URI getLocation(String id){
        return uriInfo.getAbsolutePathBuilder().path(id).build();
    }


}
