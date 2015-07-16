package com.survtower.ws.api;


import com.survtower.ws.api.domain.MemberPayload;
import java.io.Serializable;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Charles Chigoriwa
 */
@Path("/")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public interface MemberWebService extends Serializable{
    
    @Path("/members")
    @GET
    public MemberPayload getData(@QueryParam(value = "lastUpdatedNo") Long lastUpdateNo);
    
    
}
