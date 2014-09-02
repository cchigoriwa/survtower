package com.survtower.business.member.domain;

import com.survtower.business.common.BaseEntity;
import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

/**
 *
 * @author Charles Chigoriwa
 */
@MappedSuperclass
public abstract class MetaDataEntity extends BaseEntity {
    
   
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date maximumDate;

    public Date getMaximumDate() {
        return maximumDate;
    }

    public void setMaximumDate(Date maximumDate) {
        this.maximumDate = maximumDate;
    }  
    
}
