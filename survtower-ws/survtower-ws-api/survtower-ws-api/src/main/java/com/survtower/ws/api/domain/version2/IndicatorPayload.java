package com.survtower.ws.api.domain.version2;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class IndicatorPayload extends ResponsePayload {

    private IndicatorBody indicatorBody;

    public IndicatorBody getIndicatorBody() {
        return indicatorBody;
    }

    public void setIndicatorBody(IndicatorBody indicatorBody) {
        this.indicatorBody = indicatorBody;
    }

}
