package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.Indicator;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class IndicatorBody {

    private List<Indicator> indicators;

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }

}
