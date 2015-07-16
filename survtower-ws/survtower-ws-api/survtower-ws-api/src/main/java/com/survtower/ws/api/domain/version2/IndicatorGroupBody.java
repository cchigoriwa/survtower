package com.survtower.ws.api.domain.version2;

import com.survtower.business.common.domain.IndicatorGroup;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@XmlRootElement
public class IndicatorGroupBody {

    private List<IndicatorGroup> indicatorGroups;

    public List<IndicatorGroup> getIndicatorGroups() {
        return indicatorGroups;
    }

    public void setIndicatorGroups(List<IndicatorGroup> indicatorGroups) {
        this.indicatorGroups = indicatorGroups;
    }

}
