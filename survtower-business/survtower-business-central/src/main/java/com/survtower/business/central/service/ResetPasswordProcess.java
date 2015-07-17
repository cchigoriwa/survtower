package com.survtower.business.central.service;

import com.survtower.business.central.domain.ResetCentralUserPasswordRequest;
import com.survtower.business.central.domain.ResetMemberSecurityPasswordRequest;

/**
 *
 * @author Daniel Nkhoma
 */
public interface ResetPasswordProcess {

    public ResetCentralUserPasswordRequest createNewCentralUserPasswordRequest(String emailAddress);

    public ResetMemberSecurityPasswordRequest createNewLinkerPasswordRequest(String emailAddress);

}
