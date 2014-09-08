package com.survtower.business.common.domain;

import com.survtower.business.common.NamedBaseEntity;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
public class DataSource extends NamedBaseEntity {
    
    private static final long serialVersionUID = 1L;
    @ManyToOne
    private DataSourceCategory dataSourceCategory;
    @ManyToOne
    private Frequency frequency;

    public DataSourceCategory getDataSourceCategory() {
        return dataSourceCategory;
    }

    public void setDataSourceCategory(DataSourceCategory dataSourceCategory) {
        this.dataSourceCategory = dataSourceCategory;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }
        
    @Override
    public String toString() {
        return getName();
    }

}
