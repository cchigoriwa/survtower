package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.DataElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 
 */
@XmlRootElement
public class DataElementCollectionPayload extends ResponsePayload{
    
    private List<DataElement> dataElements;

    public List<DataElement> getDataElements() {
        return dataElements;
    }

    public void setDataElements(List<DataElement> dataElements) {
        this.dataElements = dataElements;
    }
    
    
    
}
