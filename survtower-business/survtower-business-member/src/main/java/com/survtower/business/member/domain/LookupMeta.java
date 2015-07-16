package com.survtower.business.member.domain;

import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.Lookup;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
public class LookupMeta extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Lookup lookup;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastServerTimestamp;
    private Long lastServerUpdateNo;

    public LookupMeta() {
    }

    public LookupMeta(Lookup lookup) {
        this.lookup = lookup;
    }

    public LookupMeta(Lookup lookup, Date lastServerTimestamp) {
        this.lookup = lookup;
        this.lastServerTimestamp = lastServerTimestamp;
    }

    public Date getLastServerTimestamp() {
        return lastServerTimestamp;
    }

    public void setLastServerTimestamp(Date lastServerTimestamp) {
        this.lastServerTimestamp = lastServerTimestamp;
    }

    public Lookup getLookup() {
        return lookup;
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public Long getLastServerUpdateNo() {
        return lastServerUpdateNo;
    }

    public void setLastServerUpdateNo(Long lastServerUpdateNo) {
        this.lastServerUpdateNo = lastServerUpdateNo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LookupMeta)) {
            return false;
        }
        LookupMeta other = (LookupMeta) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.survtower.business.member.domain.LookupMeta[ id=" + id + " ]";
    }

}
