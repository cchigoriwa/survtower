package com.survtower.business.central.domain;

import com.survtower.business.common.domain.AbstractResetPasswordRequest;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Daniel Nkhoma
 */
@Entity
public class ResetMemberSecurityPasswordRequest extends AbstractResetPasswordRequest {

    @ManyToOne(optional = false)
    @JoinColumn(updatable = false)
    private MemberSecurity memberSecurity;

    public MemberSecurity getMemberSecurity() {
        return memberSecurity;
    }

    public void setMemberSecurity(MemberSecurity memberSecurity) {
        this.memberSecurity = memberSecurity;
    }
}
