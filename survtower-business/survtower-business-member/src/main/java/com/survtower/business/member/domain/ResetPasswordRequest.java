package com.survtower.business.member.domain;

import com.survtower.business.common.domain.AbstractResetPasswordRequest;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
public class ResetPasswordRequest extends AbstractResetPasswordRequest{
    
    @ManyToOne(optional = false)
    @JoinColumn(updatable = false)
    private MemberUser memberUser; 

    public MemberUser getMemberUser() {
        return memberUser;
    }

    public void setMemberUser(MemberUser memberUser) {
        this.memberUser = memberUser;
    }

  
    
}
