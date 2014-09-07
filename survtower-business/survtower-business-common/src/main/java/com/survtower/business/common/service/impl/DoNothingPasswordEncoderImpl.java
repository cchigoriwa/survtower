package com.survtower.business.common.service.impl;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("doNothingPasswordEncoder")
public class DoNothingPasswordEncoderImpl implements PasswordEncoder{

    @Override
    public String encodePassword(String rawPass, Object salt) {
       return rawPass;
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        String internEncPass = encodePassword(rawPass, salt);
        boolean result = encPass.equals(internEncPass);
        return result;
    }
    
}
