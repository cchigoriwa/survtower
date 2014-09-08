package com.survtower.business.common.domain;

import com.survtower.business.common.NamedBaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 */
@Entity
@XmlRootElement
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"uuid"})})
public class Frequency extends NamedBaseEntity {
    
    private static final long serialVersionUID = 1L;
    private Double factor = 0.0;

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }
    
    @Override
    public String toString() {
        return getName();
    }

}
