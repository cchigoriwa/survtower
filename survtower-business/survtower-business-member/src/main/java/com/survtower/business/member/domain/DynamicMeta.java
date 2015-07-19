package com.survtower.business.member.domain;

import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.Dynamic;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
@Table(name = "dynamic_meta")
public class DynamicMeta extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Dynamic dynamic;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastLocalTimestamp;

    public DynamicMeta() {
    }

    public DynamicMeta(Dynamic dynamic) {
        this.dynamic = dynamic;
    }

    public DynamicMeta(Dynamic dynamic, Date lastLocalTimestamp) {
        this.dynamic = dynamic;
        this.lastLocalTimestamp = lastLocalTimestamp;
    }

    public Dynamic getDynamic() {
        return dynamic;
    }

    public void setDynamic(Dynamic dynamic) {
        this.dynamic = dynamic;
    }

    public Date getLastLocalTimestamp() {
        return lastLocalTimestamp;
    }

    public void setLastLocalTimestamp(Date lastLocalTimestamp) {
        this.lastLocalTimestamp = lastLocalTimestamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DynamicMeta)) {
            return false;
        }
        DynamicMeta other = (DynamicMeta) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.survtower.business.member.domain.LookupMeta[ id=" + id + " ]";
    }

}
