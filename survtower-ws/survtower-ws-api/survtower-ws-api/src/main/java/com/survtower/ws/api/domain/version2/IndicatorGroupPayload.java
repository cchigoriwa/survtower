package com.survtower.ws.api.domain.version2;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class IndicatorGroupPayload extends ResponsePayload {

    private IndicatorGroupBody indicatorGroupBody;

    public IndicatorGroupBody getIndicatorGroupBody() {
        return indicatorGroupBody;
    }

    public void setIndicatorGroupBody(IndicatorGroupBody indicatorGroupBody) {
        this.indicatorGroupBody = indicatorGroupBody;
    }

}
