package com.survtower.ws.api;

import com.survtower.ws.api.domain.PeriodPayload;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Daniel Nkhoma
 */
@Path("/")
@Produces(MediaType.APPLICATION_XML)
public interface PeriodWebService {

    @Path("/periods")
    @GET
    public PeriodPayload getData(@QueryParam(value = "lastUpdatedNo") Long lastUpdatedNo);

}
