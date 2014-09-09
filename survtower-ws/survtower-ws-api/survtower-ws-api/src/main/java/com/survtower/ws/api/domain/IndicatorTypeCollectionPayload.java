package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.IndicatorType;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 
 */
@XmlRootElement
public class IndicatorTypeCollectionPayload extends ResponsePayload{
    
    private List<IndicatorType> indicatorTypes;

    public List<IndicatorType> getIndicatorTypes() {
        return indicatorTypes;
    }

    public void setIndicatorTypes(List<IndicatorType> indicatorTypes) {
        this.indicatorTypes = indicatorTypes;
    }
    
    
    
}
