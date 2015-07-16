package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.Frequency;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class FrequencyBody {

    private List<Frequency> frequencies;

    public List<Frequency> getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(List<Frequency> frequencies) {
        this.frequencies = frequencies;
    }

}
