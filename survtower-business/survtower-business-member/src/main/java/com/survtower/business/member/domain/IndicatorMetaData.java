package com.survtower.business.member.domain;

import javax.persistence.Entity;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
public class IndicatorMetaData extends MetaDataEntity {
    private static final long serialVersionUID = 1L;    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndicatorMetaData)) {
            return false;
        }
        IndicatorMetaData other = (IndicatorMetaData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.survtower.business.member.domain.IndicatorMetaData[ id=" + id + " ]";
    }
    
}
