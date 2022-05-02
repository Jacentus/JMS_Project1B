package com.jmotyka.jms_project1b.channels.adapters.rest;


import com.jmotyka.jms_project1b.channels.adapters.persistence.NotAllowedToGetHistoryException;
import com.jmotyka.jms_project1b.channels.domain.entities.Channel;
import com.jmotyka.jms_project1b.channels.domain.ports.ChannelService;
import lombok.Setter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("channels")
@Setter
public class ChannelController {

    @Inject
    private ChannelService channelService;

    @Inject
    private RestChannelMapper channelMapper;

    @Context
    private UriInfo uriInfo;

    @Path("publicChannel")
    @POST
    public Response createPublicChannel(ChannelDTO channelDTO){
        Channel channel = channelService.createChannel(channelDTO.getChannelName());
        return Response.created(getLocation(channel.getChannelName())).build();
    }

    @Path("privateChannel")
    @POST
    public Response createPrivateChannel(ChannelDTO channelDTO){
        Channel channel = channelService.createChannel(channelDTO.getChannelName(), channelDTO.getIsPrivate(), channelDTO.getPermittedUsers());
        return Response.created(getLocation(channel.getChannelName())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPublicChannels(){
        return Response.ok(channelService.getAllPublicChannels()).build();
    }

    @Path("history/{channelName}/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getChannelHistory(@PathParam("channelName") String channelName, @PathParam("userName") String username) {
        try {
            return Response.ok(channelService.getChannelHistory(channelName, username)).build();
        } catch (NotAllowedToGetHistoryException e) {
            return Response.status(403).build();
        }
    }

    private URI getLocation(String channelName){
        return uriInfo.getAbsolutePathBuilder().path(channelName).build();
    }

}
