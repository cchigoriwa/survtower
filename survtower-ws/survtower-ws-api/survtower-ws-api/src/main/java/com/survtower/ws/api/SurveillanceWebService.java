package com.survtower.ws.api;

import com.survtower.ws.api.domain.SurveillancePayload;
import java.io.Serializable;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Charles Chigoriwa
 */
@Path("/")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public interface SurveillanceWebService extends Serializable{
    
    @Path("/surveillance")
    @POST
    public void processData(SurveillancePayload surveillancePayload);
    
    
}
