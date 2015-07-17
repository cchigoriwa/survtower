package com.survtower.business.central.service;

import com.survtower.business.central.domain.ResetCentralUserPasswordRequest;
import com.survtower.business.central.domain.ResetMemberSecurityPasswordRequest;
import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface EmailHelper extends Serializable {

    public String createCentralUserResetPasswordTextMessage(ResetCentralUserPasswordRequest resetPasswordRequest);

    public String createMemberSecurityTextMessage(ResetMemberSecurityPasswordRequest resetPasswordRequest);

    public String createCentralUserChangePasswordTextMessage(String username, String password);

}
