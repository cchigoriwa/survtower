package com.survtower.business.central.domain;

import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.Member;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
public class MemberSecurity extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    private String emailAddress;
    private String password;
    private String memberID;
    private String memberKey;    
    private boolean deactivated;
    
    @OneToOne
    private Member member;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public boolean isDeactivated() {
        return deactivated;
    }

    public void setDeactivated(boolean deactivated) {
        this.deactivated = deactivated;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MemberSecurity)) {
            return false;
        }
        MemberSecurity other = (MemberSecurity) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.survtower.business.central.domain.CountrySecurity[ id=" + id + " ]";
    }
    
}
