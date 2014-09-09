package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.Frequency;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 
 */
@XmlRootElement
public class FrequencyCollectionPayload extends ResponsePayload{
    
    private List<Frequency> frequencys;

    public List<Frequency> getFrequencys() {
        return frequencys;
    }

    public void setFrequencys(List<Frequency> frequencys) {
        this.frequencys = frequencys;
    }
    
    
    
}
