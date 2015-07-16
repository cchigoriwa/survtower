package com.survtower.ws.api.domain.version2;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class FrequencyPayload extends ResponsePayload {

    private FrequencyBody frequencyBody;

    public FrequencyBody getFrequencyBody() {
        return frequencyBody;
    }

    public void setFrequencyBody(FrequencyBody frequencyBody) {
        this.frequencyBody = frequencyBody;
    }

}
