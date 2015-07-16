package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.Lookup;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class ServerLookupMetaData implements Serializable{
    
    private Lookup lookup;
    private Date lastUpdateTimestamp;
    private Long lastUpdateNo;

    public ServerLookupMetaData() {
    }

    public ServerLookupMetaData(Lookup lookup, Date lastUpdateTimestamp) {
        this.lookup = lookup;
        this.lastUpdateTimestamp = lastUpdateTimestamp;
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

    public Date getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Date lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    public Long getLastUpdateNo() {
        return lastUpdateNo;
    }

    public void setLastUpdateNo(Long lastUpdateNo) {
        this.lastUpdateNo = lastUpdateNo;
    }
    
    
    
    
    
}
