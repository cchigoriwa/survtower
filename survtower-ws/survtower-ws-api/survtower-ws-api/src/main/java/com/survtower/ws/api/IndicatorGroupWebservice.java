package com.survtower.ws.api;

import com.survtower.ws.api.domain.IndicatorGroupCollectionPayload;
import com.survtower.ws.api.domain.RequestMetaData;
import java.io.Serializable;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Takunda Dhlakama
 */
@Path("/")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public interface IndicatorGroupWebservice extends Serializable{
    
    @Path("/indicatorGroups")
    @POST
    public IndicatorGroupCollectionPayload getIndicatorGroups(RequestMetaData requestMetaData);
    
    
}
