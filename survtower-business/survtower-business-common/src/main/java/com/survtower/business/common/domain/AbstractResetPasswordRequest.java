package com.survtower.business.common.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

/**
 *
 * @author Charles Chigoriwa
 */
@MappedSuperclass
public abstract class AbstractResetPasswordRequest implements Serializable {
    
    protected static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;  
    @Column(updatable = false,nullable = false,unique = true)
    protected String firstTag;
    @Column(updatable = false,nullable = false,unique = true)
    protected String secondTag;
    @Column(updatable = false,nullable = false)    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date timeRequested;
    @Column(updatable = false,nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date timeOfExpiry;
    @Column(nullable = true,updatable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date timeOfReset;
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstTag() {
        return firstTag;
    }

    public void setFirstTag(String firstTag) {
        this.firstTag = firstTag;
    }

    public String getSecondTag() {
        return secondTag;
    }

    public void setSecondTag(String secondTag) {
        this.secondTag = secondTag;
    }

   
   

    public Date getTimeRequested() {
        return timeRequested;
    }

    public void setTimeRequested(Date timeRequested) {
        this.timeRequested = timeRequested;
    }

    public Date getTimeOfExpiry() {
        return timeOfExpiry;
    }

    public void setTimeOfExpiry(Date timeOfExpiry) {
        this.timeOfExpiry = timeOfExpiry;
    }

    public Date getTimeOfReset() {
        return timeOfReset;
    }

    public void setTimeOfReset(Date timeOfReset) {
        this.timeOfReset = timeOfReset;
    }
    
    
    public boolean isExpired(){
        return (new Date().after(timeOfExpiry));
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
        if (!(object instanceof AbstractResetPasswordRequest)) {
            return false;
        }
        AbstractResetPasswordRequest other = (AbstractResetPasswordRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zw.co.transunion.client.business.domain.ResetPasswordRequest[ id=" + id + " ]";
    }
    
}
