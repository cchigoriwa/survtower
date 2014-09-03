package com.survtower.ws.api;

import com.survtower.ws.api.domain.LookupMetaDataCollectionPayload;
import java.io.Serializable;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
public interface LookupDataWebservice extends Serializable{

    @Path("/lookup/metadata")
    @GET
    public LookupMetaDataCollectionPayload getLookupMetaDataList();
}
