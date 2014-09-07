package com.survtower.business.common.service.impl;

import com.survtower.business.common.SurvtowerRuntimeException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("passwordEncoder")
public class PasswordEncoderImpl implements PasswordEncoder {

    @Override
    public String encodePassword(String rawPass, Object salt) throws DataAccessException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(salt.toString().getBytes("UTF-8"));
            byte[] input = digest.digest(rawPass.getBytes("UTF-8"));
            String strPassword = Base64.encodeBase64String(input);
            return strPassword;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException nse) {
            throw new SurvtowerRuntimeException(nse);
        }
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException {
        String internEncPass = encodePassword(rawPass, salt);
        boolean result = encPass.equals(internEncPass);
        return result;
    }

}
