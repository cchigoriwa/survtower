package com.survtower.business.common.service;

import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface PasswordGeneratorService extends Serializable{
    
    public String generatePassword();
    
}
