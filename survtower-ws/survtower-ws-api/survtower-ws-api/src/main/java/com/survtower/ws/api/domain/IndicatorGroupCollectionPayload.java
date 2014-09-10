package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.IndicatorGroup;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 
 */
@XmlRootElement
public class IndicatorGroupCollectionPayload extends ResponsePayload{
    
    private List<IndicatorGroup> indicatorGroups;

    public List<IndicatorGroup> getIndicatorGroups() {
        return indicatorGroups;
    }

    public void setIndicatorGroups(List<IndicatorGroup> indicatorGroups) {
        this.indicatorGroups = indicatorGroups;
    }
    
    
    
}
