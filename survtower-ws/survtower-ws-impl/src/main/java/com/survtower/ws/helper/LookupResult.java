package com.survtower.ws.helper;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Charles Chigoriwa
 */
public class LookupResult implements Serializable{
    
    private Long serverLastUpdateNo;
    private List resultList;

    public Long getServerLastUpdateNo() {
        return serverLastUpdateNo;
    }

    public void setServerLastUpdateNo(Long serverLastUpdateNo) {
        this.serverLastUpdateNo = serverLastUpdateNo;
    }

    public List getResultList() {
        return resultList;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }
    
    
    
}
