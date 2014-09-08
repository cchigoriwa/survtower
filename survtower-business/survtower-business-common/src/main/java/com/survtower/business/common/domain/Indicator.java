package com.survtower.business.common.domain;

import com.survtower.business.common.NamedBaseEntity;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
@XmlRootElement
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"uuid"})})
public class Indicator extends NamedBaseEntity {

    private static final long serialVersionUID = 1L;
    @ManyToOne
    private IndicatorGroup indicatorGroup;
    @ManyToOne
    private DataElement numerator;
    @ManyToOne
    private DataElement denominator;
    @ManyToOne
    private IndicatorType indicatorType;

    public IndicatorGroup getIndicatorGroup() {
        return indicatorGroup;
    }

    public void setIndicatorGroup(IndicatorGroup indicatorGroup) {
        this.indicatorGroup = indicatorGroup;
    }

    public DataElement getNumerator() {
        return numerator;
    }

    public void setNumerator(DataElement numerator) {
        this.numerator = numerator;
    }

    public DataElement getDenominator() {
        return denominator;
    }

    public void setDenominator(DataElement denominator) {
        this.denominator = denominator;
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

}
