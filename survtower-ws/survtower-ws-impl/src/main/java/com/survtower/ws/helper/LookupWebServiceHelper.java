package com.survtower.ws.helper;

import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface LookupWebServiceHelper extends Serializable {

    public LookupResult findLookupResult(String $Entity, Long lastUpdatedNo);
}
