package com.jmotyka.jms_project1b.channels.adapters.rest;


import com.jmotyka.jms_project1b.channels.domain.entities.Channel;
import com.jmotyka.jms_project1b.channels.domain.ports.ChannelService;
import lombok.Setter;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("channels")
@Setter
public class ChannelController {

    @Inject
    private ChannelService channelService;

    @Inject
    private RestChannelMapper channelMapper;

    @Context
    private UriInfo uriInfo;

    @POST
    public Response createPublicChannel(ChannelDTO channelDTO){
        Channel channel = channelService.createChannel(channelDTO.getChannelName());
        return Response.created(getLocation(channel.getChannelName())).build();
    }

    @POST
    public Response createPrivateChannel(ChannelDTO channelDTO){
        Channel channel = channelService.createChannel(channelDTO.getChannelName(), channelDTO.getIsPrivate(), channelDTO.getPermittedUsers());
        return Response.created(getLocation(channel.getChannelName())).build();
    }

    ///////////////////////////////////////////     tu kolejne metody ////////////////////////////////////////////

    private URI getLocation(String channelName){
        return uriInfo.getAbsolutePathBuilder().path(channelName).build();
    }

}
