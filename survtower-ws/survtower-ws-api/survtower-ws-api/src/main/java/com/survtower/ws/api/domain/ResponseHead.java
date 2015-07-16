package com.survtower.ws.api.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author charlesc
 */
@XmlRootElement
public class ResponseHead{
    
    private Long lastUpdateNo;

    public Long getLastUpdateNo() {
        return lastUpdateNo;
    }

    public void setLastUpdateNo(Long lastUpdateNo) {
        this.lastUpdateNo = lastUpdateNo;
    }
    
    
    
}
