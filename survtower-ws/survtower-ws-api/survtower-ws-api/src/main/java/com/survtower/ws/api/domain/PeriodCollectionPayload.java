package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.Period;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 
 */
@XmlRootElement
public class PeriodCollectionPayload extends ResponsePayload{
    
    private List<Period> periods;

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }
    
    
    
}
