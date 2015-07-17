package com.survtower.client.linker.helper;

import com.survtower.business.central.domain.ResetCentralUserPasswordRequest;
import com.survtower.business.central.domain.ResetMemberSecurityPasswordRequest;
import com.survtower.business.central.service.EmailHelper;
import com.survtower.client.linker.util.WebUtilityImpl;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component
public class EmailHelperImpl implements EmailHelper {

    @Override
    public String createCentralUserResetPasswordTextMessage(ResetCentralUserPasswordRequest resetPasswordRequest) {
        return null;
    }

    @Override
    public String createCentralUserChangePasswordTextMessage(String username, String password) {
        return null;
    }

    @Override
    public String createMemberSecurityTextMessage(ResetMemberSecurityPasswordRequest resetPasswordRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("Changing your password is simple. Please use the link below");
        sb.append(" within 24 hours.  ");
        String webUrl = null;
        try {
            webUrl = WebUtilityImpl.getBasePath();
        } catch (MalformedURLException ex) {
            Logger.getLogger(EmailHelperImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
//        String webUrl = "http://localhost:8080";
        sb.append(webUrl);
        sb.append("/survtower-client-linker/reset-password/");
        sb.append(resetPasswordRequest.getFirstTag());
        sb.append("&tok=");
        sb.append(resetPasswordRequest.getSecondTag());
        return sb.toString();
    }
}
