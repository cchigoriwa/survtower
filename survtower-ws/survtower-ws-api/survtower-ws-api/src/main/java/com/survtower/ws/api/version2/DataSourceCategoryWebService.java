package com.survtower.ws.api.version2;

import com.survtower.ws.api.domain.version2.DataSourceCategoryPayload;
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
public interface DataSourceCategoryWebService {

    @Path("/dataSourceCategories")
    @GET
    public DataSourceCategoryPayload getData(@QueryParam(value = "lastUpdatedNo") Long lastUpdateNo);

}
