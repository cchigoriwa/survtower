package com.survtower.business.common.service.impl;

import com.survtower.business.common.service.PasswordGeneratorService;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Charles Chigoriwa
 */
public class DynamicPasswordGeneratorService implements PasswordGeneratorService{

    @Override
    public String generatePassword() {
        String rawPassword = RandomStringUtils.randomAlphanumeric(10);
        return rawPassword;
    }
    
}
