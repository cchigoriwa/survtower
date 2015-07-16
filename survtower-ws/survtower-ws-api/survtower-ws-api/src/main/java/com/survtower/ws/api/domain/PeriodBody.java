package com.survtower.ws.api.domain;

import com.survtower.business.common.domain.Period;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel Nkhoma
 */
@XmlRootElement
public class PeriodBody {

    private List<Period> periods;

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }
}
