package com.survtower.client.central.helper;

import com.survtower.business.central.domain.ResetCentralUserPasswordRequest;
import com.survtower.business.central.domain.ResetMemberSecurityPasswordRequest;
import com.survtower.business.central.service.EmailHelper;
import com.survtower.client.central.utility.WebUtilityImpl;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component
public class EmailHelperImpl implements EmailHelper {

    @Override
    public String createCentralUserResetPasswordTextMessage(ResetCentralUserPasswordRequest resetPasswordRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("Changing your password is simple. Please use the link below");
        sb.append(" within 24 hours.  ");
        String webUrl = WebUtilityImpl.getBasePath();
        sb.append(webUrl);
        sb.append("/faces/resetPassword.xhtml?reset=");
        sb.append(resetPasswordRequest.getFirstTag());
        sb.append("&tok=");
        sb.append(resetPasswordRequest.getSecondTag());
        return sb.toString();
    }

    @Override
    public String createCentralUserChangePasswordTextMessage(String username, String password) {
        StringBuilder sb = new StringBuilder();
        sb.append("Your Central/SADC Secretariat account was successfully created");
        sb.append("Your login details: Username: ");
        sb.append(username);
        sb.append(" Password: ");
        sb.append(password);
        sb.append(" You are encouraged to change this password upon first login.");
        sb.append(" To access the system please click the link below: ");
        String webUrl = WebUtilityImpl.getBasePath();
        sb.append(webUrl);
        return sb.toString();
    }

    @Override
    public String createMemberSecurityTextMessage(ResetMemberSecurityPasswordRequest resetPasswordRequest) {
        return null;
    }
}
