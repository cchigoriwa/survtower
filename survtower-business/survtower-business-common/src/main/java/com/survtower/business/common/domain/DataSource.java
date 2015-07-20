package com.survtower.business.common.domain;

import com.survtower.business.common.NamedBaseEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 */
@Entity
@XmlRootElement
@Table(name = "data_source")
public class DataSource extends NamedBaseEntity {

    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name="data_source_category_id")
    private DataSourceCategory dataSourceCategory;
    @ManyToOne
    @JoinColumn(name="frequency_id")
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
