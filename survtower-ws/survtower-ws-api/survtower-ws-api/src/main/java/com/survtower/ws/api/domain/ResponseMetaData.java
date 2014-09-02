package com.survtower.ws.api.domain;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class ResponseMetaData implements Serializable{
    
    private Date maximumDate;

    public Date getMaximumDate() {
        return maximumDate;
    }

    public void setMaximumDate(Date maximumDate) {
        this.maximumDate = maximumDate;
    }
    
    
    
    
}
