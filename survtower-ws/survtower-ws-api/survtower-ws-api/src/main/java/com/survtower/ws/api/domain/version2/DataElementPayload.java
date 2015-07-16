package com.survtower.ws.api.domain.version2;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class DataElementPayload extends ResponsePayload{
    
    private DataElementBody dataElementBody;

    public DataElementBody getDataElementBody() {
        return dataElementBody;
    }

    public void setDataElementBody(DataElementBody dataElementBody) {
        this.dataElementBody = dataElementBody;
    }
    
    
}
