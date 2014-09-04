package com.survtower.business.common.domain;

import com.survtower.business.common.BaseEntity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
public class Period extends BaseEntity {
    private static final long serialVersionUID = 1L;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date validFrom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date validTo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dueDate;
    private Boolean active=Boolean.FALSE;

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Period)) {
            return false;
        }
        Period other = (Period) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.survtower.business.common.domain.Period[ id=" + id + " ]";
    }
    
}
