package com.survtower.business.central.domain;

import com.survtower.business.common.domain.AbstractResetPasswordRequest;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Daniel Nkhoma
 */
@Entity
@Table(name = "reset_member_security_password_request")
public class ResetMemberSecurityPasswordRequest extends AbstractResetPasswordRequest {

    @ManyToOne(optional = false)
    @JoinColumn(name="member_security_id",updatable = false)
    private MemberSecurity memberSecurity;

    public MemberSecurity getMemberSecurity() {
        return memberSecurity;
    }

    public void setMemberSecurity(MemberSecurity memberSecurity) {
        this.memberSecurity = memberSecurity;
    }
}
