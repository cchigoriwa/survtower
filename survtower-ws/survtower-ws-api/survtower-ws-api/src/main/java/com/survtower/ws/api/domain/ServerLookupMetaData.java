package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.Lookup;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class ServerLookupMetaData implements Serializable {

    private Lookup lookup;
    private Long lastUpdateNo;

    public ServerLookupMetaData() {
    }

    public ServerLookupMetaData(Lookup lookup, Long lastUpdateNo) {
        this.lookup = lookup;
        this.lastUpdateNo = lastUpdateNo;
    }

    public Lookup getLookup() {
        return lookup;
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public Long getLastUpdateNo() {
        return lastUpdateNo;
    }

    public void setLastUpdateNo(Long lastUpdateNo) {
        this.lastUpdateNo = lastUpdateNo;
    }

}
