package com.survtower.ws.api.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel Nkhoma
 */
@XmlRootElement
public class PeriodPayload extends ResponsePayload {

    private PeriodBody periodBody;

    public PeriodBody getPeriodBody() {
        return periodBody;
    }

    public void setPeriodBody(PeriodBody periodBody) {
        this.periodBody = periodBody;
    }
}
