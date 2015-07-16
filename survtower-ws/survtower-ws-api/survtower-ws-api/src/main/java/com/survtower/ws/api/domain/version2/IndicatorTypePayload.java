package com.survtower.ws.api.domain.version2;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class IndicatorTypePayload extends ResponsePayload {

    private IndicatorTypeBody indicatorTypeBody;

    public IndicatorTypeBody getIndicatorTypeBody() {
        return indicatorTypeBody;
    }

    public void setIndicatorTypeBody(IndicatorTypeBody indicatorTypeBody) {
        this.indicatorTypeBody = indicatorTypeBody;
    }

}
