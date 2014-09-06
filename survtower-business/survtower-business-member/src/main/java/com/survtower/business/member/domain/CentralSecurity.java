package com.survtower.business.member.domain;

import com.survtower.business.common.BaseEntity;
import javax.persistence.Entity;

/**
 *
 * @author Charles Chigoriwa
 * 
 */
@Entity
public class CentralSecurity extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    private String memberID;
    private String memberPassword;

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
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
