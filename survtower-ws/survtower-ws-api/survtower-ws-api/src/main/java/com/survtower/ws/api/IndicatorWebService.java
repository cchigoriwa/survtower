package com.survtower.ws.api;

import com.survtower.ws.api.domain.IndicatorPayload;
import java.io.Serializable;
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
public interface IndicatorWebService extends Serializable {

    @Path("/indicators")
    @GET
    public IndicatorPayload getData(@QueryParam(value = "lastUpdatedNo") Long lastUpdateNo);

}
