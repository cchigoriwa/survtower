package com.survtower.ws.api.domain;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public abstract class ResponsePayload implements Serializable{
    
    protected ResponseMetaData payloadMetaData;

    public ResponseMetaData getPayloadMetaData() {
        return payloadMetaData;
    }

    public void setPayloadMetaData(ResponseMetaData payloadMetaData) {
        this.payloadMetaData = payloadMetaData;
    }
    
    
    
}
