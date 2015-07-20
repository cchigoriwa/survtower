package com.survtower.business.common.domain;

import com.survtower.business.common.NamedBaseEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
@XmlRootElement
@Table(name = "indicator")
public class Indicator extends NamedBaseEntity {

    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name="indicator_group_id")
    private IndicatorGroup indicatorGroup;
    @ManyToOne
    @JoinColumn(name="numerator_data_element_id")
    private DataElement numeratorDataElement;
    @ManyToOne
    @JoinColumn(name="denominator_data_element_id")
    private DataElement denominatorDataElement;
    @ManyToOne
    @JoinColumn(name="indicator_type_id")
    private IndicatorType indicatorType;

    public IndicatorGroup getIndicatorGroup() {
        return indicatorGroup;
    }

    public void setIndicatorGroup(IndicatorGroup indicatorGroup) {
        this.indicatorGroup = indicatorGroup;
    }

    public DataElement getNumeratorDataElement() {
        return numeratorDataElement;
    }

    public void setNumeratorDataElement(DataElement numerator) {
        this.numeratorDataElement = numerator;
    }

    public DataElement getDenominatorDataElement() {
        return denominatorDataElement;
    }

    public void setDenominatorDataElement(DataElement denominator) {
        this.denominatorDataElement = denominator;
    }

    public IndicatorType getIndicatorType() {
        return indicatorType;
    }

    public void setIndicatorType(IndicatorType indicatorType) {
        this.indicatorType = indicatorType;
    }

    @Override
    public String toString() {
        return getName();
    }

    public Boolean getIndicatorIsACount() {
        if (getIndicatorType() != null && 1 == getIndicatorType().getFactor()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

}
