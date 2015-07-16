package com.survtower.ws.api.version2;

import com.survtower.ws.api.domain.version2.ProgramPayload;
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
public interface ProgramWebService extends Serializable {

    @Path("/programs")
    @GET
    public ProgramPayload getData(@QueryParam(value = "lastUpdatedNo") Long lastUpdateNo);

}
