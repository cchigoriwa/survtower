package com.survtower.business.member.domain;

import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.Lookup;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
@Table(name = "lookup_meta")
public class LookupMeta extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name="lookup_id",unique = true)
    private Lookup lookup;
    @Column(name="last_server_update_no")
    private Long lastServerUpdateNo;

    public LookupMeta() {
    }

    public LookupMeta(Lookup lookup) {
        this.lookup = lookup;
    }

    public LookupMeta(Lookup lookup, Long lastServerUpdateNo) {
        this.lookup = lookup;
        this.lastServerUpdateNo = lastServerUpdateNo;
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

}
