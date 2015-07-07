package com.survtower.business.member.domain;

import com.survtower.business.common.BaseEntity;
import java.util.Objects;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 */
@Entity
@XmlRootElement
public class MemberUserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;
   
    private String memberRole;
    private Boolean deactivated = Boolean.FALSE;

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public Boolean getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Boolean deactivated) {
        this.deactivated = deactivated;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.memberRole);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MemberUserRole other = (MemberUserRole) obj;
        if (!Objects.equals(this.memberRole, other.memberRole)) {
            return false;
        }
        return true;
    }

 

    
    
}
