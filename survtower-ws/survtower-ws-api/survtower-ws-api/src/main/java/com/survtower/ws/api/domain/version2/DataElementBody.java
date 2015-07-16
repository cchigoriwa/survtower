package com.survtower.ws.api.domain.version2;

import com.survtower.business.common.domain.DataElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class DataElementBody {
    
    private List<DataElement> dataElements;

    public List<DataElement> getDataElements() {
        return dataElements;
    }

    public void setDataElements(List<DataElement> dataElements) {
        this.dataElements = dataElements;
    }
    
    
    
}
