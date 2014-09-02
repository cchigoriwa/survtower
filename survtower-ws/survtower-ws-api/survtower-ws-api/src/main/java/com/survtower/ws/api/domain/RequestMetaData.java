package com.survtower.ws.api.domain;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class RequestMetaData implements Serializable{
    
    protected Date minimumDate;

    public Date getMinimumDate() {
        return minimumDate;
    }

    public void setMinimumDate(Date minimumDate) {
        this.minimumDate = minimumDate;
    }
    
}
