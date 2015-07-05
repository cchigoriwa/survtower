package com.survtower.business.common.service.impl;

import com.survtower.business.common.service.PasswordGeneratorService;

/**
 *
 * @author Charles Chigoriwa
 */
public class ConstantPasswordGeneratorService implements PasswordGeneratorService{

    @Override
    public String generatePassword() {
       return "test1234";
    }
    
}
