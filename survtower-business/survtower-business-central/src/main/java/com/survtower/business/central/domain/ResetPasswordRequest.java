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
@Table
public class ResetPasswordRequest extends AbstractResetPasswordRequest{
    
    @ManyToOne(optional = false)
    @JoinColumn(updatable = false)
    private CentralUser centralUser; 

    public CentralUser getCentralUser() {
        return centralUser;
    }

    public void setCentralUser(CentralUser centralUser) {
        this.centralUser = centralUser;
    }
}
