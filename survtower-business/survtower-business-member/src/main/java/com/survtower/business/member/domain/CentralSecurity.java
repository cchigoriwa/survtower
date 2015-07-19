package com.survtower.business.member.domain;

import com.survtower.business.common.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Charles Chigoriwa
 *
 */
@Entity
@Table(name = "central_security")
public class CentralSecurity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String centralServerUrl;
    private String memberID;
    private String memberKey;

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberKey() {
        return memberKey;
    }

    public void setMemberKey(String memberKey) {
        this.memberKey = memberKey;
    }

    public String getCentralServerUrl() {
        return centralServerUrl;
    }

    public void setCentralServerUrl(String centralServerUrl) {
        this.centralServerUrl = centralServerUrl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CentralSecurity)) {
            return false;
        }
        CentralSecurity other = (CentralSecurity) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.survtower.business.member.domain.CentralSecurity[ id=" + id + " ]";
    }

}
