package com.survtower.business.common.domain;

import com.survtower.business.common.NamedBaseEntity;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 */
@Entity
@XmlRootElement
public class DataSourceCategory extends NamedBaseEntity {
    
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return getName();
    }

}
