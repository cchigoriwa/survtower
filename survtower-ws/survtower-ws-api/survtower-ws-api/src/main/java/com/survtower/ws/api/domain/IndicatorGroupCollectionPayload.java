package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.Indicator;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 
 */
@XmlRootElement
public class IndicatorGroupCollectionPayload extends ResponsePayload{
    
    private List<Indicator> indicators;

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }
    
    
    
}
