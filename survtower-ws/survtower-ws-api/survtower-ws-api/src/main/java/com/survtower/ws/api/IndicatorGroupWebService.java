package com.survtower.ws.api;

import com.survtower.ws.api.domain.IndicatorGroupPayload;
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
public interface IndicatorGroupWebService extends Serializable {

    @Path("/indicatorGroups")
    @GET
    public IndicatorGroupPayload getData(@QueryParam(value = "lastUpdatedNo") Long lastUpdateNo);

}
