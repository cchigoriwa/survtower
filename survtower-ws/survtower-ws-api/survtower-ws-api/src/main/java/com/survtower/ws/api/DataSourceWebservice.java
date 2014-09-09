package com.survtower.ws.api;

import com.survtower.ws.api.domain.DataSourceCollectionPayload;
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
public interface DataSourceWebservice extends Serializable{
    
    @Path("/dataSources")
    @POST
    public DataSourceCollectionPayload getDataSources(RequestMetaData requestMetaData);
    
    
}
