package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.Surveillance;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class SurveillancePayload {
    
    private List<Surveillance> surveillances;

    public SurveillancePayload() {
    }

    public SurveillancePayload(List<Surveillance> surveillances) {
        this.surveillances = surveillances;
    }
    
    public List<Surveillance> getSurveillances() {
        return surveillances;
    }

    public void setSurveillances(List<Surveillance> surveillances) {
        this.surveillances = surveillances;
    }
    
    
    
}
