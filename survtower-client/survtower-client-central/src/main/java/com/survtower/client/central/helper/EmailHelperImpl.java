package com.survtower.client.central.helper;

import com.survtower.business.central.domain.MemberSecurity;
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

    @Override
    public String createMemberSecurityTextMessage(MemberSecurity memberSecurity, String password) {
        StringBuilder sb = new StringBuilder();
        sb.append("Dear ");
        sb.append(memberSecurity.getEmailAddress());
        sb.append("\n\n");
        sb.append("The SADC Secretariat Surveillance System has successfully created your Member State Security details.");
        sb.append("The detail are as follows");
        sb.append("\n\n");
        sb.append("Member ID: ");
        sb.append(memberSecurity.getMemberID());
        sb.append("\n\n");
        sb.append("Member Key: ");
        sb.append(memberSecurity.getMemberKey());
        sb.append("\n\n");
        sb.append("To log in the Secretariat External portal for managing your security details use the following details.");
        sb.append("\n\n");
        sb.append("Username: ");
        sb.append(memberSecurity.getEmailAddress());
        sb.append("\n\n");
        sb.append("Password: ");
        sb.append(password);
        sb.append(" You are encouraged to change this password upon first login.");
        return sb.toString();
    }
}
