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

    public ServerLookupMetaData() {
    }

    public ServerLookupMetaData(Lookup lookup, Date lastUpdateTimestamp) {
        this.lookup = lookup;
        this.lastUpdateTimestamp = lastUpdateTimestamp;
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
    
    
    
}
