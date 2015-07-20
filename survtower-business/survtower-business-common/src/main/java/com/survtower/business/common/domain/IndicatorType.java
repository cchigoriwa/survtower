package com.survtower.business.common.domain;

import com.survtower.business.common.NamedBaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 */
@Entity
@XmlRootElement
@Table(name = "indicator_type")
public class IndicatorType extends NamedBaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name="decimal_places")
    private Integer decimalPlaces = 0;
    @Column(name="factor")
    private Integer factor = 1;

    public Integer getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(Integer decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public Integer getFactor() {
        return factor;
    }

    public void setFactor(Integer factor) {
        this.factor = factor;
    }

    @Override
    public String toString() {
        return getName();
    }

}
