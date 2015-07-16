package com.survtower.ws.api;

import com.survtower.ws.api.domain.DataSourcePayload;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author charlesc
 */
@Path("/")
@Produces(MediaType.APPLICATION_XML)
public interface DataSourceWebService {

    @Path("/dataSources")
    @GET
    public DataSourcePayload getData(@QueryParam(value="lastUpdatedNo") Long lastUpdateNo);

}
